package PIERJEP_LAB_02;

import java.util.List;

/**
 * Name: Jephte Pierre
 * Date: January 29, 2024

 * This class contains methods to perform various tasks related to arrays and matrices.
 */
public class Main {

    /**
     * Main method to test the functionality of the class.
     *
     */
    public static void main(String[] args) {
        // Test display name
        displayName();

        // Test display array
        int[] array1D = {1, 2, 3, 4, 5};
        displayArray(array1D);

        // Test display 2D array
        int[][] array2D = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        displayArray(array2D);

        // Test equality of 1D arrays
        int[] array1D_1 = {1, 2, 3};
        int[] array1D_2 = {1, 2, 3};
        System.out.println("1D Arrays are equal: " + areArraysEqual(array1D_1, array1D_2));

        // Test equality of 2D arrays
        int[][] array2D_1 = {
                {1, 2},
                {3, 4}
        };
        int[][] array2D_2 = {
                {1, 2},
                {3, 4}
        };
        System.out.println("2D Arrays are equal: " + areArraysEqual(array2D_1, array2D_2));

        // Test sum of 1D array
        int[] array1DSum = {1, 2, 3, 4, 5};
        System.out.println("Sum of 1D Array: " + sumArray(array1DSum));

        // Test sum of 2D array
        int[][] array2DSum = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("Sum of 2D Array: " + sumArray(array2DSum));

        // Test matrix multiplication
        int[][] matA = {
                {1, 2},
                {4, 1}
        };
        int[][] matB = {
                {2, 2},
                {2, 2}
        };
        int[][] result = multiplyMatrices(matA, matB);
        System.out.println("Matrix Multiplication Result:");
        displayArray(result);
    }

    /**
     * Task 1: Print your full name.
     */
    public static void displayName() {
        System.out.println("Jephte Pierre");
    }

    /**
     * Task 2: Print a 1-dimensional int array.
     *
     * @param array The 1-dimensional array to be displayed.
     */
    public static void displayArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Task 3: Print a 2-dimensional int array.
     *
     * @param array The 2-dimensional array to be displayed.
     */
    public static void displayArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /**
     * Task 4: Determine if two 1-dimensional arrays are equal.
     *
     * @param array1 The first 1-dimensional array.
     * @param array2 The second 1-dimensional array.
     * @return true if the arrays are equal, false otherwise.
     */
    public static boolean areArraysEqual(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Task 5: Determine if two 2-dimensional arrays are equal.
     *
     * @param array1 The first 2-dimensional array.
     * @param array2 The second 2-dimensional array.
     * @return true if the arrays are equal, false otherwise.
     */
    public static boolean areArraysEqual(int[][] array1, int[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Task 6: Find the sum of a 1-dimensional array.
     *
     * @param array The 1-dimensional array.
     * @return The sum of elements in the array.
     */
    public static int sumArray(int[] array) {
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

    /**
     * Task 7: Find the sum of a 2-dimensional array.
     *
     * @param array The 2-dimensional array.
     * @return The sum of elements in the array.
     */
    public static int sumArray(int[][] array) {
        int sum = 0;
        for (int[] row : array) {
            for (int element : row) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * Task 8: Multiply two matrices (2-dimensional arrays) element-wise.
     *
     * @param matA The first matrix.
     * @param matB The second matrix.
     * @return The element-wise multiplication result as a 2-dimensional array.
     */
    public static int[][] multiplyMatrices(int[][] matA, int[][] matB) {
        int rowsA = matA.length;
        int colsA = matA[0].length;
        int[][] result = new int[rowsA][colsA];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                result[i][j] = matA[i][j] * matB[i][j];
            }
        }

        return result;
    }
}

