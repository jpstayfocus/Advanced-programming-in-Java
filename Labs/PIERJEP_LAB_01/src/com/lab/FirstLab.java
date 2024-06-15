/*
    * Name: Jephte Pierre
    * Date: January 15th, 2024
    * Description: This program runs "Hello World".
*/

package com.lab;

import java.util.Scanner;

public class FirstLab {

    public static void main(String[] args) {

        // Take user input for their name
        System.out.println("Hello, what is your name?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Hello " + input + ":-)");
        System.out.println("I am glad that you are my Instructor for CS321 this Winter");
        System.out.println();

        // Use a do-while loop to ask a question at least once
        boolean answer;
        do {
            // Ask the user if they want to hear good news
            System.out.println("Do you want me to share a good news with you " + input + "?");
            System.out.println("yes or No");

            // Get the user's choice
            String choice = scanner.nextLine();

            // Process the user's choice using a switch statement
            answer = switch (choice.toLowerCase()) {  // Make the comparison case-insensitive
                case "yes" -> {
                    // Share good news if the user chooses "yes"
                    System.out.println("I have created my first program, 'Hello World' :-)");
                    yield false;  // Set answer to false to exit the loop
                }
                case "no" -> {
                    // Inform the user if they choose "no"
                    System.out.println("Sorry, you'll have to run the program again");
                    yield false;  // Set answer to false to exit the loop
                }
                default -> {
                    // Handle invalid input and prompt the user again
                    System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
                    yield true;  // Keep the loop running for invalid input
                }
            };
        } while (answer);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}
