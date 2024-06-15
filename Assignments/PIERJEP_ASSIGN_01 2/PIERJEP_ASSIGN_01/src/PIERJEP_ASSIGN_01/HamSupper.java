package PIERJEP_ASSIGN_01;

/**
 * Name: Jephte Pierre
 * Date: January 29, 2024
 * The HamSupper class computes the sum of serial numbers to be cleaned in the dishwasher and sink
 * based on the hamming distance of the serial numbers.
 */

import java.util.List;

/* MARKER-COMMENT:
 *       Submission Guidelines (-0.5):
 *           - Missing docs folder. The docs were output into the top-level of your project folder instead.
 *       Formatting (-0.5):
 *           - Code lacks some comments to explain what you're trying to do.
 *       Part 1 (-5):
 *           - The sums calculated are including the same serial numbers multiple times.
 *           - Should be using double for the sums, not int.
 *           - Specialized method is O(n^2), but it should be O(n).
 *       Testing (-2.5):
 *           - No testing with the given input file.
 *  */

public class HamSupper {

    /**
     * Main method for testing the dishwasher and sink sums calculation.
     *
     * @param args The command line arguments (unused).
     */
    public static void main(String[] args) {
        // Provided input list
        List<String> serialNumbers = List.of("0010", "1214", "3897");

        // General code
        int dishwasherSum = computeDishwasherSum(serialNumbers);
        int sinkSum = computeSinkSum(serialNumbers);

        System.out.println("Dishwasher sum: " + dishwasherSum);
        System.out.println("Sink sum: " + sinkSum);

        // Specialized code with linear time complexity
        int[] specializedResult = computeSumsLinearTime(serialNumbers);
        System.out.println("Specialized Dishwasher sum: " + specializedResult[0]);
        System.out.println("Specialized Sink sum: " + specializedResult[1]);
    }

    /**
     * Computes the sum of serial numbers to be cleaned in the dishwasher.
     *
     * @param serialNumbers The list of serial numbers.
     * @return The sum of serial numbers to be cleaned in the dishwasher.
     */
    private static int computeDishwasherSum(List<String> serialNumbers) {
        int dishwasherSum = 0;

        for (int i = 0; i < serialNumbers.size(); i++) {
            for (int j = i + 1; j < serialNumbers.size(); j++) {
                int hammingDistance = calculateHammingDistance(serialNumbers.get(i), serialNumbers.get(j));
                if (hammingDistance < serialNumbers.getFirst().length()) {
                    dishwasherSum += Integer.parseInt(serialNumbers.get(i)) + Integer.parseInt(serialNumbers.get(j));
                }
            }
        }

        return dishwasherSum;
    }

    /**
     * Computes the sum of serial numbers to be cleaned in the sink.
     *
     * @param serialNumbers The list of serial numbers.
     * @return The sum of serial numbers to be cleaned in the sink.
     */
    private static int computeSinkSum(List<String> serialNumbers) {
        int sinkSum = 0;

        for (int i = 0; i < serialNumbers.size(); i++) {
            for (int j = i + 1; j < serialNumbers.size(); j++) {
                int hammingDistance = calculateHammingDistance(serialNumbers.get(i), serialNumbers.get(j));
                if (hammingDistance == serialNumbers.getFirst().length()) {
                    /* MARKER-COMMENT: Should only be added to the sink if it's length is equal to the length
                    when compared with ALL other serial numbers, not just one. Also, you shouldn't be adding the
                    other serial number to the sink at the same time (it might need to go to the dishwasher).
                     */
                    sinkSum += Integer.parseInt(serialNumbers.get(i)) + Integer.parseInt(serialNumbers.get(j));
                }
            }
        }

        return sinkSum;
    }

    /**
     * Calculates the hamming distance between two strings.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The hamming distance between the two strings.
     */
    private static int calculateHammingDistance(String s1, String s2) {
        int distance = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Computes the sums of serial numbers to be cleaned in the dishwasher and sink with linear time complexity.
     *
     * @param serialNumbers The list of serial numbers.
     * @return An array containing the dishwasher sum at index 0 and the sink sum at index 1.
     */
    private static int[] computeSumsLinearTime(List<String> serialNumbers) {
        int dishwasherSum = 0;
        int sinkSum = 0;

        for (int i = 0; i < serialNumbers.size(); i++) {
            for (int j = i + 1; j < serialNumbers.size(); j++) {
                int hammingDistance = calculateHammingDistance(serialNumbers.get(i), serialNumbers.get(j));
                int sum = Integer.parseInt(serialNumbers.get(i)) + Integer.parseInt(serialNumbers.get(j));

                if (hammingDistance < serialNumbers.getFirst().length()) {
                    dishwasherSum += sum;
                } else if (hammingDistance == serialNumbers.getFirst().length()) {
                    /* MARKER-COMMENT: See comment in the non-linear method.
                     */
                    sinkSum += sum;
                }
            }
        }

        return new int[]{dishwasherSum, sinkSum};
    }
}

