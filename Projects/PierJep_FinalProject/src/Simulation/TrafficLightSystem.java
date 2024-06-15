package Simulation;


/**
 * The TrafficLightSystem class represents a system that manages traffic lights.
 * It follows the Singleton pattern, ensuring that only one instance exists.
 */
public class TrafficLightSystem {
    private static TrafficLightSystem instance;
    private TrafficLight trafficLight;

    /**
     * Constructs a TrafficLightSystem object and initializes the traffic light.
     */
    TrafficLightSystem() {
        this.trafficLight = new TrafficLight();
    }

    /**
     * Retrieves the single instance of the TrafficLightSystem class.
     * If the instance does not exist, it creates a new one.
     *
     * @return The single instance of TrafficLightSystem.
     */
    public static TrafficLightSystem getInstance(){
        if(instance == null){
            instance = new TrafficLightSystem();
        }
        return instance;
    }

    /**
     * Changes the color of the traffic light managed by the system.
     *
     * @param newColor The new color to set for the traffic light.
     */
    public void changeLight(LightColor newColor){
        trafficLight.changeColor(newColor);
    }

    /**
     * Retrieves the current color of the traffic light managed by the system.
     *
     * @return The current color of the traffic light.
     */
    public LightColor getCurrentLightColor(){
        return trafficLight.getColor();
    }
}


