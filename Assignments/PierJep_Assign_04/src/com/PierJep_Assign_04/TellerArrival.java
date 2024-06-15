/**
 * Represents an arrival event for a teller at the bank simulation.
 */

package com.PierJep_Assign_04;


public class TellerArrival extends Event {
    private final BankSim bankSim;

    /**
     * Constructs a TellerArrival event.
     *
     * @param time    The time at which the teller arrives.
     * @param bankSim The bank simulation instance.
     */
    public TellerArrival(int time, BankSim bankSim) {
        super(time);
        this.bankSim = bankSim;
    }

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
