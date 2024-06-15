package Simulation;


/**
 * The MoveForwardStrategy class represents a strategy for moving a vehicle forward.
 * It implements the MovementStrategy interface.
 */
public class MoveForwardStrategy implements MovementStrategy {
    private TrafficLightSystem trafficLightSystem;

    /**
     * Constructs a MoveForwardStrategy object with the specified traffic light system.
     *
     * @param trafficLightSystem The traffic light system to be used for checking light colors.
     */
    public MoveForwardStrategy(TrafficLightSystem trafficLightSystem) {
        this.trafficLightSystem = trafficLightSystem;
    }

    /**
     * Moves the vehicle forward according to the strategy.
     * If the traffic light is not red, the vehicle moves forward based on its speed.
     * If the traffic light is red, the vehicle waits at its current position.
     *
     * @param vehicle The vehicle to be moved.
     */
    @Override
    public void move(Vehicle vehicle) {
        if (trafficLightSystem.getCurrentLightColor() != LightColor.RED) {
            int newPosition = vehicle.getPosition() + vehicle.getSpeed();
            vehicle.setPosition(newPosition);
            System.out.println(vehicle.getName() + " move forward to position " + newPosition);
        } else {
            System.out
                    .println(vehicle.getName() + " is at position " + vehicle.getPosition() + ", waiting at red light");
        }
    }
}

