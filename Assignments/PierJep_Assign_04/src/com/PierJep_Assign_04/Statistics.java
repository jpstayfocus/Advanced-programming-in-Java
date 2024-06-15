/**
 * Represents the statistics collected during the bank simulation.
 * It tracks the waiting times of clients in the system.
 */

package com.PierJep_Assign_04;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final List<Integer> waitingTimes; // List to store waiting times of clients

    public Statistics() {
        this.waitingTimes = new ArrayList<>(); // Initialize the list
    }

    /**
     * Adds a waiting time to the statistics.
     *
     * @param time The waiting time to be added.
     */
    public void addWaitingTime(int time) {
        waitingTimes.add(time);
    }

    public double getAverageWaitingTime() {
        if (waitingTimes.isEmpty()) {
            return 0.0; // If there are no recorded waiting times, return 0.0
        }
        int sum = 0;
        for (int time : waitingTimes) {
            sum += time; // Calculate the sum of waiting times
        }
        return (double) sum / waitingTimes.size(); // Calculate and return the average waiting time
    }
}
