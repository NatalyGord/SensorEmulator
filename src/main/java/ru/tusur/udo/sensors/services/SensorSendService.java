package ru.tusur.udo.sensors.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tusur.udo.sensors.interfaces.SensorsRuntime;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
public class SensorSendService extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(SensorSendService.class);

    @Value("${polling.interval}")
    private int pollingInterval;

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    SensorsRuntime sensorsRuntime;

    @Autowired
    NodeInfoService nodeInfoService;

    private URL url;
    private HttpURLConnection http;


    @PostConstruct
    public void init() throws MalformedURLException {
        url = new URL(serverUrl);
        start();
    }

    public void run() {

        while(true) {
            send(nodeInfoService.serialize(sensorsRuntime.getSensors()));
            try {
                sleep(pollingInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void send(String payload) {
        final byte[] out = payload.getBytes(StandardCharsets.UTF_8);
        try (OutputStream os = getOutputStream(out.length)) {
            if (os != null) os.write(out);
        } catch(IOException e) {
            LOG.error("Sending error: " + e.getMessage());
        }
    }

    private OutputStream getOutputStream(int length) {
        try {
            URLConnection urlConnection = url.openConnection();
            http = (HttpURLConnection) urlConnection;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.setFixedLengthStreamingMode(length);
            http.connect();
            return http.getOutputStream();
        } catch (IOException e) {
            LOG.error("CONNECTION ERROR: " + e.getMessage());
        }
        return null;
    }


}
