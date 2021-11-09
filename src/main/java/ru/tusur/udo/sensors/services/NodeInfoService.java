package ru.tusur.udo.sensors.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tusur.udo.sensors.dto.NodeInfo;
import ru.tusur.udo.sensors.dto.SensorInfo;
import ru.tusur.udo.sensors.interfaces.Sensor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NodeInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(NodeInfoService.class);

    @Value("${node.name}")
    private String nodeName;

    @Value("${sensor.mode}")
    private String sensorMode;

    public String serialize(List<Sensor> sensors)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(makeNodeInfo(sensors));
        } catch (Exception e) {
            LOG.error("ERROR " + e.getMessage());
        }
        return null;
    }


    private NodeInfo makeNodeInfo(List<Sensor> sensors) {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeName(nodeName);
        nodeInfo.setTimeStamp(new Date().getTime());
        nodeInfo.setSensorsInfo(sensors
                .stream()
                .map(sensor -> {
                    SensorInfo sensorInfo = new SensorInfo();
                    sensorInfo.setMode(sensorMode);
                    sensorInfo.setType(sensor.getType());
                    sensorInfo.setName(sensor.getName());
                    sensorInfo.setValue(sensor.getValue());
                    return sensorInfo;
                }).collect(Collectors.toList())
        );
        return nodeInfo;
    }


}
