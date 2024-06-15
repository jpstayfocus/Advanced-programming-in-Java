package Simulation;

import org.junit.Test;
import Skeleton.SimulationInput;

import static org.junit.Assert.*;

import java.util.concurrent.Semaphore;

public class MoveForwardStrategyTest {
    @Test
    public void testMove() {
        // Create a mock SimulationInput object (can be null for this test)
        SimulationInput input = null;

        // Create a mock Semaphore object (not used in this test)
        Semaphore semaphore = null;

        // Create a mock TrafficLightSystem object
        TrafficLightSystem trafficLightSystem = new TrafficLightSystem();

        // Create a mock Vehicle object
        Vehicle vehicle = new Vehicle("TestVehicle", input, semaphore, trafficLightSystem);
        vehicle.setPosition(0);
        vehicle.setSpeed(5);

        // Create a MoveForwardStrategy object
        MoveForwardStrategy moveForwardStrategy = new MoveForwardStrategy(trafficLightSystem);

        // Call the move method of MoveForwardStrategy
        moveForwardStrategy.move(vehicle);

        // Check if the vehicle's position has been updated correctly
        assertEquals(5, vehicle.getPosition());
    }
}


