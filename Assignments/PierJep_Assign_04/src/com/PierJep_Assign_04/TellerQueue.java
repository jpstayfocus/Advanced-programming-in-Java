package com.PierJep_Assign_04;

import java.util.LinkedList;
import java.util.Queue;

public class TellerQueue {
    private final Queue<Client> queue;

    // Constructor initializes the queue
    public TellerQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds a client to the teller queue.
     * @param client The client to be added to the queue.
     */
    public void add(Client client) {
        queue.offer(client);
    }

    /**
     * Retrieves and removes the next client from the queue.
     * @return The next client in the queue, or null if the queue is empty.
     */
    public Client getNextClient() {
        return queue.poll();
    }

    /**
     * Checks if the teller queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Removes and returns the next client from the queue.
     * This method is an alias for getNextClient().
     * @return The next client in the queue, or null if the queue is empty.
     */
    public Client remove() {
        return getNextClient();
    }
}