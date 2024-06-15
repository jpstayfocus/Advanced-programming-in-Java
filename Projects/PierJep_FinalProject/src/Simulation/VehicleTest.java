package Simulation;


import Skeleton.SimulationInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleTest {
    private Vehicle vehicle;
    private TrafficLightSystem trafficLightSystem;

    @Before
    public void setUp() {
        SimulationInput input = new SimulationInput();
        trafficLightSystem = new TrafficLightSystem(); // Initialize a mock TrafficLightSystem
        vehicle = new Vehicle("Car", input, null, trafficLightSystem); // Pass null for semaphore
    }

    @Test
    public void testPerformAction() {
        // Set initial position and speed
        vehicle.setPosition(0);
        vehicle.setSpeed(5);

        // Perform action
        vehicle.performAction();

        // Verify position after movement
        assertEquals(5, vehicle.getPosition());
        System.out.println("testPerformAction passed");
    }

    @Test
    public void testSubmitStatistics() {
        // Perform action multiple times
        for (int i = 0; i < 3; i++) {
            vehicle.performAction();
            vehicle.submitStatistics();
        }

        // Check if the number of actions performed is recorded correctly
        assertEquals(3, vehicle.getStats().getStatistic("VehicleActionsPerformed").summarize(), 0);
        System.out.println("testSubmitStatistics passed");
    }

    @Test
    public void testSetMovementStrategy() {
        // Create a new movement strategy
        MovementStrategy newStrategy = new StopStrategy();

        // Set the new movement strategy
        vehicle.setMovementStrategy(newStrategy);

        // Perform action
        vehicle.performAction();

        // Verify that the vehicle stopped (position should not change)
        assertEquals(0, vehicle.getPosition());
        System.out.println("testSetMovementStrategy passed");
    }
}

