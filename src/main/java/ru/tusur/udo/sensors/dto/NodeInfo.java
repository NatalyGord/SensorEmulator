package ru.tusur.udo.sensors.dto;

import java.util.List;

public class NodeInfo {

    private String nodeName;
    long timeStamp;
    List<SensorInfo> sensorsInfo;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<SensorInfo> getSensorsInfo() {
        return sensorsInfo;
    }

    public void setSensorsInfo(List<SensorInfo> sensorsInfo) {
        this.sensorsInfo = sensorsInfo;
    }
}
