package Simulation;

/**
 * The LightColor class represents the possible colors of a traffic light.
 */
public class LightColor {
    /**
     * Represents the RED color of a traffic light.
     */
    public static final LightColor RED = new LightColor("RED");

    /**
     * Represents the YELLOW color of a traffic light.
     */
    public static final LightColor YELLOW = new LightColor("YELLOW");

    /**
     * Represents the GREEN color of a traffic light.
     */
    public static final LightColor GREEN = new LightColor("GREEN");

    private final String color; // The color of the traffic light

    // Private constructor to prevent external instantiation
    private LightColor(String color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the traffic light color.
     *
     * @return The color of the traffic light as a string.
     */
    @Override
    public String toString() {
        return color;
    }
}



