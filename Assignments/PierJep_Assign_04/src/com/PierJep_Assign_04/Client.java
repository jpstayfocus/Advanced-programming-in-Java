/**
 * Represents a client in the bank simulation.
 */
package com.PierJep_Assign_04;

import java.util.Random;

public class Client {
    private final int arrivalTime; // Stores the time the client arrived
    private final int transactions; // Stores the number of transactions for the client

    /**
     * Constructs a Client object with the specified arrival time.
     *
     * @param arrivalTime The time at which the client arrived.
     */
    public Client(int arrivalTime) {
        this.arrivalTime = arrivalTime; // Set the arrival time
        // Generate a random number of transactions between 1 and 100
        this.transactions = new Random().nextInt(100) + 1;
    }

    /**
     * Gets the number of transactions for the client.
     *
     * @return The number of transactions.
     */
    public int getTransactions() {
        return transactions;
    }

    /**
     * Gets the arrival time of the client.
     *
     * @return The arrival time.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }
}
