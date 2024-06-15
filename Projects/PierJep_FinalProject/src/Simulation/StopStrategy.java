package Simulation;


/**
 * The StopStrategy class represents a strategy for stopping a vehicle.
 * It implements the MovementStrategy interface.
 */
public class StopStrategy implements MovementStrategy {

    /**
     * Stops the vehicle at its current position.
     *
     * @param vehicle The vehicle to be stopped.
     */
    @Override
    public void move(Vehicle vehicle){
        System.out.println(vehicle.getName() + " stops at position " + vehicle.getPosition());
    }
}
