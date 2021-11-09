package ru.tusur.udo.sensors.emulator;

public interface EmulationStrategy {
    void setTicksCount(int count);
    void doEmulate(FakeSensor fakeSensor);
}
