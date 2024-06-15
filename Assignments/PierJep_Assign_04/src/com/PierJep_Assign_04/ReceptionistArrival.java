/**
 * Represents an arrival event for a client at the receptionist in the bank simulation.
 * Extends the Event class.
 */
package com.PierJep_Assign_04;


public class ReceptionistArrival extends Event {
    private final BankSim bankSim;

    /**
     * Constructs a ReceptionistArrival object
     *
     * @param time    The arrival time of the event.
     * @param bankSim The BankSim object associated with the event.
     */
    public ReceptionistArrival(int time, BankSim bankSim) {
        super(time); // Call the constructor of the superclass (Event)
        this.bankSim = bankSim; // Set the BankSim object
    }

    @Override
    public void process() {
        // Move the clock to the time of the arrival event
        bankSim.setClock(getTime());

        // Create a client object with the current time of the clock as arrival time
        Client client = new Client(getTime());

        // If the receptionist queue is not empty, add the client to the queue
        // Else, set the receptionist to serve the client and schedule a departure event
        if (!bankSim.getReceptionistQueue().isEmpty() || bankSim.getReceptionist() != null) {
            bankSim.getReceptionistQueue().add(client);
            System.out.println("Client added to receptionist queue at time: " + getTime());
        } else {
            bankSim.setReceptionist(client);
            int serviceTime = (int) RandBox.expo(bankSim.getAvgTimePerTransaction() * client.getTransactions());
            int departureTime = getTime() + serviceTime;
            bankSim.scheduleEvent(new ReceptionistDeparture(departureTime, bankSim));
        }

        // Record waiting time if client is queued
        if (bankSim.getReceptionist() != null && bankSim.getReceptionist() != client) {
            int waitingTime = getTime() - client.getArrivalTime();
            bankSim.getStatistics().addWaitingTime(waitingTime);
        }

        // Schedule a new ReceptionistArrival at time NOW + RandBox.expo(INTERVAL_TIME)
        int nextArrivalTime = getTime() + (int) RandBox.expo(bankSim.getIntervalTime());
        if (nextArrivalTime < bankSim.getClock() + 1)
            nextArrivalTime = bankSim.getClock() + 1; // Ensure next arrival is at least 1 second ahead
        bankSim.scheduleEvent(new ReceptionistArrival(nextArrivalTime, bankSim));
    }
}