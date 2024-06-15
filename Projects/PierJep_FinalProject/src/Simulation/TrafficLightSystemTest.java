package Simulation;


import org.junit.Test;
import static org.junit.Assert.*;

public class TrafficLightSystemTest {
    @Test
    public void testChangeLight() {
        // Create a TrafficLightSystem object
        TrafficLightSystem trafficLightSystem = TrafficLightSystem.getInstance();

        // Check the initial light color
        assertEquals(LightColor.GREEN, trafficLightSystem.getCurrentLightColor());

        // Change the light color to RED
        trafficLightSystem.changeLight(LightColor.RED);
        assertEquals(LightColor.RED, trafficLightSystem.getCurrentLightColor());

        // Change the light color to YELLOW
        trafficLightSystem.changeLight(LightColor.YELLOW);
        assertEquals(LightColor.YELLOW, trafficLightSystem.getCurrentLightColor());

        // Change the light color back to GREEN
        trafficLightSystem.changeLight(LightColor.GREEN);
        assertEquals(LightColor.GREEN, trafficLightSystem.getCurrentLightColor());
    }
}


