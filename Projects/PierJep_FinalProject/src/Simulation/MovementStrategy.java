package Simulation;


/**
 * The MovementStrategy interface represents a strategy for moving a vehicle.
 * Different implementations of this interface define different movement behaviors.
 */
public interface MovementStrategy {

    /**
     * Moves the vehicle according to the strategy.
     *
     * @param vehicle The vehicle to be moved.
     */
    void move(Vehicle vehicle);
}