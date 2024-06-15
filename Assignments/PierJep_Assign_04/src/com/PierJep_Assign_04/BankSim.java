/**
 * Name: Jephte Pierre
 * Date: Wednesday 27th, 2024
 * Description: BankSim simulates the operation of a bank. It manages the event queue, receptionist queue,
 * teller queue, client objects, simulation clock, and statistics related to the bank simulation.
 */
package com.PierJep_Assign_04;

import java.util.PriorityQueue;

public class BankSim {
    private final PriorityQueue<Event> eventQueue; // Priority queue to manage events
    private final ReceptionistQueue receptionistQueue; // Queue for clients waiting for the receptionist
    private final TellerQueue tellerQueue; // Queue for clients waiting for tellers
    private Client receptionist; // Current client being served by the receptionist
    private Client teller; // Current client being served by a teller
    private int clock; // Current simulation time
    private final double INTERVAL_TIME; // Average inter-arrival time of clients
    private final double AVG_TIME_PER_TRANSACTION; // Average time taken to process each transaction
    private final Statistics statistics; // Object to store and calculate statistics

    /**
     * Constructor to initialize the BankSim object with given parameters.
     *
     * @param intervalTime           The average inter-arrival time of clients.
     * @param avgTimePerTransaction  The average time taken to process each transaction.
     */
    public BankSim(double intervalTime, double avgTimePerTransaction) {
        this.eventQueue = new PriorityQueue<>();
        this.receptionistQueue = new ReceptionistQueue();
        this.tellerQueue = new TellerQueue();
        this.INTERVAL_TIME = intervalTime;
        this.AVG_TIME_PER_TRANSACTION = avgTimePerTransaction;
        this.clock = 0;
        this.receptionist = null;
        this.teller = null;
        this.statistics = new Statistics();
    }

    /**
     * Starts the bank simulation.
     *
     * @param simulationTime  The duration of the simulation in seconds.
     */

    public void run(int simulationTime) {
        System.out.println("Simulation started.");
        scheduleFirstArrival();

        while (!eventQueue.isEmpty() && clock < simulationTime) { // Check if eventQueue is empty or simulation time exceeds the specified end time
            Event event = eventQueue.poll(); // Get the next event
            assert event != null;
            clock = event.getTime(); // Update the simulation time
            System.out.println("Processing event at time: " + clock);
            event.process(); // Process the event
        }

        System.out.println("Simulation ended.");
        printStatistics(); // Print simulation statistics
    }

    /**
     * Schedules the first arrival event at the receptionist.
     */
    private void scheduleFirstArrival() {
        int firstArrivalTime = (int) RandBox.expo(INTERVAL_TIME); // Generate arrival time
        System.out.println("Scheduling first arrival at time: " + firstArrivalTime);
        eventQueue.offer(new ReceptionistArrival(firstArrivalTime, this)); // Schedule the arrival event
    }

    /**
     * Schedules an event in the event queue.
     *
     * @param event  The event to be scheduled.
     */
    public void scheduleEvent(Event event) {
        eventQueue.offer(event); // Add the event to the priority queue
    }

    /**
     * Retrieves the receptionist queue.
     *
     * @return  The receptionist queue.
     */
    public ReceptionistQueue getReceptionistQueue() {
        return receptionistQueue;
    }

    /**
     * Retrieves the teller queue.
     *
     * @return  The teller queue.
     */
    public TellerQueue getTellerQueue() {
        return tellerQueue;
    }

    /**
     * Retrieves the current receptionist.
     *
     * @return  The receptionist.
     */
    public Client getReceptionist() {
        return receptionist;
    }

    /**
     * Sets the receptionist.
     *
     * @param receptionist  The receptionist to be set.
     */
    public void setReceptionist(Client receptionist) {
        this.receptionist = receptionist;
    }

    /**
     * Retrieves the current teller.
     *
     * @return  The teller.
     */
    public Client getTeller() {
        return teller;
    }

    /**
     * Sets the teller.
     *
     * @param teller  The teller to be set.
     */
    public void setTeller(Client teller) {
        this.teller = teller;
    }

    /**
     * Retrieves the current simulation clock time.
     *
     * @return  The simulation clock time.
     */
    public int getClock() {
        return clock;
    }

    /**
     * Retrieves the average inter-arrival time of clients.
     *
     * @return  The average inter-arrival time.
     */
    public double getIntervalTime() {
        return INTERVAL_TIME;
    }

    /**
     * Retrieves the average time taken to process each transaction.
     *
     * @return  The average time per transaction.
     */
    public double getAvgTimePerTransaction() {
        return AVG_TIME_PER_TRANSACTION;
    }

    /**
     * Retrieves the statistics object.
     *
     * @return  The statistics object.
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Prints the simulation statistics.
     */
    private void printStatistics() {
        System.out.println("Average Waiting Time: " + statistics.getAverageWaitingTime());
    }

    /**
     * The main method to start the bank simulation.
     *
     * @param args  Command line arguments (not used).
     */
    public static void main(String[] args) {
        BankSim bankSim = new BankSim(120.0, 60.0); // Create BankSim instance
        bankSim.run(5 * 8 * 60 * 60); // Run simulation for 5 8-hour workdays
    }

    /**
     * Sets the simulation clock time.
     *
     * @param time  The time to set.
     */
    public void setClock(int time) {
        this.clock = time;
    }
}
