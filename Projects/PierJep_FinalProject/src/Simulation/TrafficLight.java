package Simulation;


/**
 * The TrafficLight class represents a traffic light that can change its color.
 */
public class TrafficLight {
    private LightColor color; // The current color of the traffic light

    /**
     * Constructs a TrafficLight object with an initial state of GREEN.
     */
    public TrafficLight(){
        this.color = LightColor.GREEN; // Initial State
    }

    /**
     * Changes the color of the traffic light to the specified color.
     *
     * @param newColor The new color to set for the traffic light.
     */
    public void changeColor(LightColor newColor){
        this.color = newColor;
        System.out.println("Traffic light changed to " + newColor);
    }

    /**
     * Gets the current color of the traffic light.
     *
     * @return The current color of the traffic light.
     */
    public LightColor getColor(){
        return color;
    }
}
