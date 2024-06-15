/**
 * Name: Jephte Pierre
 * Date: March 11, 2024
 * Description: TThis Java program implements an orchestra management system, defining instruments
 * (Viola, Cello, Tuba), an Orchestra class for managing them, and offers an alternative implementation
 * using ArrayLists, with testing for functionality.
 */

package PierJep_Lab_06;

import java.util.ArrayList;
import java.util.Iterator;

// Interface representing an instrument
interface Instrument {
    /**
     * Method to play the instrument.
     */
    void play();

    /**
     * Method to tune the instrument to a note.
     * @param note The note to tune the instrument to.
     */
    void tune(char note);
}

// Class representing Viola instrument
class Viola implements Instrument {
    private final String song;

    // Constructor
    Viola(String song) {
        this.song = song;
    }

    @Override
    public void play() {
        System.out.println("Viola playing: " + song);
    }

    @Override
    public void tune(char note) {
        System.out.println("Tuning Viola to note: " + note);
    }
}

// Class representing Cello instrument
class Cello implements Instrument {
    private final String song;

    // Constructor
    Cello(String song) {
        this.song = song;
    }

    @Override
    public void play() {
        System.out.println("Cello playing: " + song);
    }

    @Override
    public void tune(char note) {
        System.out.println("Tuning Cello to note: " + note);
    }
}

// Class representing Tuba instrument
class Tuba implements Instrument {
    private final String song;
    private final int oomPaPaCount;

    // Constructor
    Tuba(String song, int oomPaPaCount) {
        this.song = song;
        this.oomPaPaCount = oomPaPaCount;
    }

    @Override
    public void play() {
        System.out.println("Tuba playing: " + song);
        for (int i = 0; i < oomPaPaCount; i++) {
            System.out.println("oom pa pa");
        }
    }

    @Override
    public void tune(char note) {
        System.out.println("Tuning Tuba to note: " + note);
    }
}

// Class representing an Orchestra
class Orchestra implements Iterable<Instrument> {
    private Instrument[] instruments; // Array of instruments
    private int size; // Current size of the orchestra
    private int capacity; // Capacity of the orchestra

    // Constructor
    Orchestra() {
        this.capacity = 10; // Initial capacity
        this.instruments = new Instrument[capacity];
        this.size = 0;
    }

    /**
     * Method to add an instrument to the orchestra.
     * @param instrument The instrument to add.
     */
    public synchronized void addInstrument(Instrument instrument) {
        if (instrument != null) {
            if (size == capacity) {
                increaseCapacity();
            }
            instruments[size++] = instrument;
        }
    }

    // Method to increase the capacity of the orchestra
    private void increaseCapacity() {
        int newCapacity = capacity + (capacity / 2);
        Instrument[] newInstruments = new Instrument[newCapacity];
        System.arraycopy(instruments, 0, newInstruments, 0, size);
        instruments = newInstruments;
        capacity = newCapacity;
    }

    /**
     * Method to play all instruments in the orchestra.
     */
    public void playAll() {
        for (Instrument instrument : instruments) {
            if (instrument != null) {
                instrument.play();
            }
        }
    }

    /**
     * Method to tune all instruments in the orchestra to a single note.
     * @param note The note to tune all instruments to.
     */
    public void tuneAll(char note) {
        for (Instrument instrument : instruments) {
            if (instrument != null) {
                instrument.tune(note);
            }
        }
    }

    @Override
    public Iterator<Instrument> iterator() {
        return new Iterator<Instrument>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && instruments[index] != null;
            }

            @Override
            public Instrument next() {
                return instruments[index++];
            }

            @Override
            public void remove() {
                if (index > 0 && index <= size) {
                    System.arraycopy(instruments, index + 1, instruments, index, size - index - 1);
                    instruments[--size] = null;
                }
            }

        };
    }
}

// Usage of the orchestra
class GenOrchestra {
    public static void main(String[] args) {
        // Using ArrayList
        ArrayList<Instrument> orchestra = new ArrayList<>();

        // Adding instruments to the orchestra
        orchestra.add(new Viola("Bach: suites for solo Violoncello"));
        orchestra.add(new Cello("Andrew Macdonald: The Great Square of Pegasus"));
        orchestra.add(new Tuba("Bach: Symphony no 1 to 4", 3));

        // Anonymous class representing a Trumpet added to the Orchestra
        orchestra.add(new Instrument() {
            @Override
            public void play() {
                System.out.println("Trumpet playing something special");
            }

            @Override
            public void tune(char note) {
                System.out.println("Tuning Trumpet to note: " + note);
            }
        });

        // Playing all instruments in the orchestra
        for (Instrument instrument : orchestra) {
            instrument.play();
        }

        // Tuning all instruments in the orchestra to a single note
        for (Instrument instrument : orchestra) {
            instrument.tune('C');
        }
    }
}
