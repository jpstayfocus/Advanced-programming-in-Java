package PIERJEP_LAB_03;


/**
 * Name: Jephte Pierre
 * Date : February 5, 2024
 * Lab 03: Objects, and Class Methods - find the distance between two points and check if
 * the point array point and return a boolean.
 */

import java.util.Arrays;

/**
 * Part 1: A class representing a point in a two-dimensional plane.
 */
public class MyPoint {
    private double x;
    private double y;

    /**
     * Part 1: Constructs a point at the origin (0, 0).
     */
    public MyPoint() {
        this(0.0, 0.0);
    }

    /**
     * Part 1: Constructs a point with the specified x (2.0) and y (4.0) co-ordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters and Setters

    /**
     * Part 1: Gets the x-coordinate of the point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Part 1: Sets the x-coordinate of the point.
     *
     * @param x The new x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Part 1: Gets the y-coordinate of the point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Part 1: Sets the y-coordinate of the point.
     *
     * @param y The new y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    // toString method

    /**
     * Part 2: Returns a string representation of the point.
     *
     * @return A string representation of the point in the format "(x, y)".
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Instance methods

    /**
     * Part 2: Calculates the Euclidean distance between this point and another point.
     *
     * @param anotherPoint The other point.
     * @return The Euclidean distance between the two points.
     */
    public double distance(MyPoint anotherPoint) {
        double dx = this.x - anotherPoint.getX();
        double dy = this.y - anotherPoint.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Part 2: Calculates the Euclidean distance between this point and a specified point.
     *
     * @param x The x-coordinate of the specified point.
     * @param y The y-coordinate of the specified point.
     * @return The Euclidean distance between this point and the specified point.
     */
    public double distance(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Class methods

    /**
     * Part 3: Checks if a point is contained in an array of points.
     *
     * @param points The array of points.
     * @param p      The point to check.
     * @return true if the point is in the array, otherwise false.
     */
    public static boolean contains(MyPoint[] points, MyPoint p) {
        return Arrays.asList(points).contains(p);
    }

    /**
     * Part 3: Determines if an array of points is in a horizontal line.
     *
     * @param points The array of points.
     * @return true if the points are in a horizontal line, otherwise false.
     */
    public static boolean horizontal(MyPoint[] points) {
        if (points.length < 2) return false;

        double y = points[0].getY();
        for (MyPoint point : points) {
            if (point.getY() != y) {
                return false;
            }
        }
        return true;
    }

    /**
     * Part 3: Main method to test the MyPoint class.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Test constructors and toString method
        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint(2.0, 4.0);
        System.out.println("Point 1: " + p1);
        System.out.println("Point 2: " + p2);

        // Test distance method
        System.out.println("Euclidean Distance: Distance between p1 and p2: " + p1.distance(p2));

        // Test contains method
        MyPoint[] points = {p1, p2};
        MyPoint p3 = new MyPoint(2.0, 4.0);
        System.out.println("Does points array contain p3? " + contains(points, p3));

        // Test horizontal method
        System.out.println("Are points in a horizontal line? " + horizontal(points));
    }
}

