/**
 * A utility class for generating random numbers.
 */

package com.PierJep_Assign_04;

public class RandBox {
    /**
     * Generates a random number from an exponential distribution with the specified mean.
     *
     * @param mean The mean of the exponential distribution.
     * @return A random number generated from the exponential distribution.
     */
    public static double expo(double mean) {
        // Generate a random number between 0 and 1
        double x = Math.random();
        // Calculate the exponential random number using the formula -mean * ln(x)
        x = -mean * Math.log(x);
        return x;
    }
}
