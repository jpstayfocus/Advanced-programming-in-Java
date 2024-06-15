/**
 * Represents a departure event for a client from the receptionist in the bank simulation.
 * Extends the Event class.
 */

package com.PierJep_Assign_04;

public class ReceptionistDeparture extends Event {
    private final BankSim bankSim;

    /**
     * Constructs a ReceptionistDeparture object with the specified departure time and BankSim object.
     *
     * @param time    The departure time of the event.
     * @param bankSim The BankSim object associated with the event.
     */
    public ReceptionistDeparture(int time, BankSim bankSim) {
        super(time); // Call the constructor of the superclass (Event)
        this.bankSim = bankSim; // Set the BankSim object
    }

    @Override
    public void process() {
        // Move the clock to the time of the departure event
        bankSim.setClock(getTime());

        // Set the receptionist to null
        bankSim.setReceptionist(null);

        // If the client queue for the receptionist is not empty, remove the first client,
        // set them to the receptionist, and schedule a new ReceptionistDeparture event
        if (!bankSim.getReceptionistQueue().isEmpty()) {
            Client nextClient = bankSim.getReceptionistQueue().remove();
            bankSim.setReceptionist(nextClient);
            int serviceTime = (int) RandBox.expo(bankSim.getAvgTimePerTransaction() * nextClient.getTransactions());
            int nextDepartureTime = getTime() + serviceTime;
            bankSim.scheduleEvent(new ReceptionistDeparture(nextDepartureTime, bankSim));

            // Calculate the time the client has been in the queue and give that time to the statistics object
            int waitingTime = getTime() - nextClient.getArrivalTime();
            bankSim.getStatistics().addWaitingTime(waitingTime);

        }
    }
}
