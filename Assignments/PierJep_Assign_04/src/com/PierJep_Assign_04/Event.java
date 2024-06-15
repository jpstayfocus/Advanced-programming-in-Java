/**
 * Abstract class representing an event in the bank simulation.
 */
package com.PierJep_Assign_04;

public abstract class Event implements Comparable<Event> {
    protected int time;

    /**
     * Constructs an Event object with the specified time.
     *
     * @param time The time at which the event occurs.
     */
    public Event(int time) {
        this.time = time;
    }

    public abstract void process();

    /**
     * Compares this event with another event based on their time.
     * * @return A negative integer, zero, or a positive integer as this event is less than, equal to,
     *         or greater than the specified event.
     */
    @Override
    public int compareTo(Event other) {
        return Integer.compare(this.time, other.time);
    }

    /**
     * Gets the time at which the event occurs.
     *
     * @return The time of the event.
     */
    public int getTime() {
        return time;
    }
}
