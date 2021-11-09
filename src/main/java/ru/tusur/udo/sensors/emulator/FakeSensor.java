package ru.tusur.udo.sensors.emulator;

import ru.tusur.udo.sensors.interfaces.Sensor;

public class FakeSensor implements Sensor {

    private String name;
    private String type;
    private int value;
    private EmulationStrategy emulationStrategy;

    public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
        this.emulationStrategy = emulationStrategy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
      return value;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void emulate() {
        emulationStrategy.doEmulate(this);
    }

}
