package Simulation;

import java.util.concurrent.Semaphore;

import Skeleton.SimulationInput;
import Skeleton.Unit;
import Skeleton.WorkerStatistic;

/**
 * The Vehicle class represents a vehicle in the simulation.
 * It extends the Unit class and implements the Runnable interface for concurrent execution.
 */
public class Vehicle extends Unit {
    private int position; // The current position of the vehicle
    private int speed;  // The speed of the vehicle
    private MovementStrategy movementStrategy; // The strategy for vehicle movement
    private Semaphore semaphore; // Introduce semaphore for interactivity
    private TrafficLightSystem trafficLightSystem; // trafficlightsystem instance

    /**
     * Constructs a Vehicle object with the specified name, input, semaphore, and traffic light system.
     *
     * @param name              The name of the vehicle.
     * @param input             The simulation input parameters.
     * @param semaphore         The semaphore for interactivity.
     * @param trafficLightSystem The traffic light system instance.
     */
    public Vehicle(String name, SimulationInput input, Semaphore semaphore, TrafficLightSystem trafficLightSystem) {
        super(name, input);
        // Initialize the "VehicleActionsPerformed" statistic
        this.getStats().addStatistic("VehicleActionsPerformed", new WorkerStatistic("VehicleActionsPerformed"));
        this.movementStrategy = new MoveForwardStrategy(trafficLightSystem);
        // Assign Semaphore
        this.semaphore = semaphore;
        // Assign trafficLightSystem instance
        this.trafficLightSystem = trafficLightSystem;
    }

    /**
     * The run method for vehicle's thread, responsible for vehicle's actions during simulation.
     */
    @Override
    public void run() {
        SimulationInput input = getSimInput(); // Retrieve input from the Unit class
        String name = getName(); // Retrieve name from the Unit class

        for (int i = 0; i < Integer.parseInt(input.getInput("Time").get(0)); i++) {
            // System.out.println(name + " is performing action at position " + position); // Output information
            try {
                semaphore.acquire(); // acquire semaphore for interactivity
                if (trafficLightSystem.getCurrentLightColor() == LightColor.RED) {
                    System.out.println(name + " is at position " + position + ", waiting at red light");
                } else {
                    // movementStrategy.move(this);
                    submitStatistics();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // release semaphore
            }
        }
    }

    /**
     * Sets the position of the vehicle.
     *
     * @param position The new position of the vehicle.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets the current position of the vehicle.
     *
     * @return The current position of the vehicle.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the speed of the vehicle.
     *
     * @param speed The speed of the vehicle.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the speed of the vehicle.
     *
     * @return The speed of the vehicle.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the movement strategy for the vehicle.
     *
     * @param movementStrategy The movement strategy to set.
     */
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * Performs the action of the vehicle according to its movement strategy.
     */
    @Override
    public void performAction() {
        movementStrategy.move(this);
    }


    /**
     * Submits statistics for the action performed by the vehicle.
     */
    @Override
    public void submitStatistics() {
        this.getStats().getStatistic("VehicleActionsPerformed").addValue(1);
    }
}