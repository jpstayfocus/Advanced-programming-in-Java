/**
 * Represents a departure event for a teller at the bank simulation.
 * This event occurs when a teller finishes serving a client.
 */

package com.PierJep_Assign_04;

public class TellerDeparture extends Event {
    private final BankSim bankSim;

    /**
     * Constructs a TellerDeparture event with the specified departure time and bank simulation.
     *
     * @param time    The time at which the teller departs.
     * @param bankSim The bank simulation instance.
     */
    public TellerDeparture(int time, BankSim bankSim) {
        super(time); // Call the constructor of the superclass (Event)
        this.bankSim = bankSim; // Set the bank simulation reference
    }

    /**
     * Processes the TellerDeparture event.
     */
    @Override
    public void process() {
        bankSim.setClock(getTime());

        Client client = new Client(getTime());

        if (bankSim.getTeller() != null) {
            bankSim.getTellerQueue().add(client);
        } else {
            bankSim.setTeller(client);
            int serviceTime = (int) RandBox.expo(bankSim.getAvgTimePerTransaction() * client.getTransactions());
            int departureTime = getTime() + serviceTime;
            bankSim.scheduleEvent(new TellerDeparture(departureTime, bankSim));
        }

        int nextArrivalTime = getTime() + (int) RandBox.expo(bankSim.getIntervalTime());
        bankSim.scheduleEvent(new TellerArrival(nextArrivalTime, bankSim));
    }
}
