package ru.tusur.udo.sensors.emulator;

public class DigitalEmulationStrategy implements EmulationStrategy {

    private int ticksCount;
    private int ticks;

    @Override
    public void setTicksCount(int count) {
        ticksCount = count;
    }

    @Override
    public void doEmulate(FakeSensor fakeSensor) {
        ticks ++;

        if (ticks == ticksCount) {
          ticks = 0;
            if (fakeSensor.getValue() == 0) {
                fakeSensor.setValue(1);
            } else {
                fakeSensor.setValue(0);
            }
        }

    }
}
