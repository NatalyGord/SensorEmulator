import org.junit.jupiter.api.Test;
import ru.tusur.udo.sensors.emulator.DigitalEmulationStrategy;
import ru.tusur.udo.sensors.emulator.EmulationStrategy;
import ru.tusur.udo.sensors.emulator.FakeSensor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalStrategyTest {

    @Test
    public void digitalSensor1() {
        int expectedValue = 0;
        int ticksCount = 3;

        EmulationStrategy digitalEmulationStrategy = new DigitalEmulationStrategy();
        digitalEmulationStrategy.setTicksCount(ticksCount);

        FakeSensor fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(digitalEmulationStrategy);

        for (int i = 1; i < 17367; i++) {
            fakeSensor.emulate();
            if (i % ticksCount == 0) {
                if (expectedValue == 0) {
                    expectedValue = 1;
                } else {
                    expectedValue = 0;
                }
            }
            assertEquals(expectedValue, fakeSensor.getValue());
        }
    }

    @Test
    public void digitalSensor2() {
        int expectedValue = 0;
        int ticksCount = 13;

        EmulationStrategy digitalEmulationStrategy = new DigitalEmulationStrategy();
        digitalEmulationStrategy.setTicksCount(ticksCount);
        FakeSensor fakeSensor = new FakeSensor();
        fakeSensor.setEmulationStrategy(digitalEmulationStrategy);

        for (int i = 1; i < 23545; i++) {
            fakeSensor.emulate();
            if (i % ticksCount == 0) {
                if (expectedValue == 0) {
                    expectedValue = 1;
                } else {
                    expectedValue = 0;
                }
            }
            assertEquals(expectedValue, fakeSensor.getValue());
        }
    }

}
