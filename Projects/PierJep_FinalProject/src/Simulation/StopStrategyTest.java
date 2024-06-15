package Simulation;

import org.junit.Test;

import Skeleton.SimulationInput;
import Skeleton.StatisticsContainer;

import static org.junit.Assert.*;

import java.util.concurrent.Semaphore;

public class StopStrategyTest {
    @Test
    public void testMove() {
        // Create a mock SimulationInput object (can be null for this test)
        SimulationInput input = null;

        // Create a mock Semaphore object (not used in this test)
        Semaphore semaphore = null;

        TrafficLightSystem trafficLightSystem = null;


        // Create a mock Vehicle object
        Vehicle vehicle = new Vehicle("TestVehicle", input, semaphore, trafficLightSystem);
        vehicle.setPosition(10); // Set an initial position

        // Create a StopStrategy object
        StopStrategy stopStrategy = new StopStrategy();

        // Call the move method of StopStrategy
        stopStrategy.move(vehicle);

        // Check if the vehicle's position remains unchanged
        assertEquals(10, vehicle.getPosition());

        // Ensure StatisticsContainer is instantiated
        StatisticsContainer.getInstance();
    }
}

