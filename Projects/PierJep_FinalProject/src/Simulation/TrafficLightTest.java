package Simulation;



import org.junit.Test;
import static org.junit.Assert.*;

public class TrafficLightTest {
    @Test
    public void testChangeColor() {
        // Create a TrafficLight object
        TrafficLight trafficLight = new TrafficLight();

        // Check the initial color
        assertEquals(LightColor.GREEN, trafficLight.getColor());

        // Change the color to RED
        trafficLight.changeColor(LightColor.RED);
        assertEquals(LightColor.RED, trafficLight.getColor());

        // Change the color to YELLOW
        trafficLight.changeColor(LightColor.YELLOW);
        assertEquals(LightColor.YELLOW, trafficLight.getColor());

        // Change the color back to GREEN
        trafficLight.changeColor(LightColor.GREEN);
        assertEquals(LightColor.GREEN, trafficLight.getColor());
    }
}

