/**
 * Represents a queue of clients waiting at the receptionist in the bank simulation.
 */
package com.PierJep_Assign_04;

import java.util.LinkedList;
import java.util.Queue;

public class ReceptionistQueue {
    private final Queue<Client> queue; // The queue to store clients

    public ReceptionistQueue() {
        this.queue = new LinkedList<>(); // Initialize the queue as a LinkedList
    }

    /**
     * Adds a client to the receptionist queue.
     *
     * @param client The client to be added to the queue.
     */
    public void addClient(Client client) {
        queue.offer(client); // Add the client to the end of the queue
    }

    /**
     * Retrieves and removes the next client from the receptionist queue.
     *
     * @return The next client in the queue, or null if the queue is empty.
     */
    public Client getNextClient() {
        return queue.poll(); // Remove and return the next client in the queue, or null if the queue is empty
    }

    /**
     * Checks if the receptionist queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Adds a client to the receptionist queue.
     * This method is equivalent to addClient(Client client).
     *
     * @param client The client to be added to the queue.
     */
    public void add(Client client) {
        addClient(client); // Delegate to the addClient method
    }

    /**
     * Removes and returns the next client from the receptionist queue.
     * This method is equivalent to getNextClient().
     *
     * @return The next client in the queue, or null if the queue is empty.
     */
    public Client remove() {
        return getNextClient();
    }
}
