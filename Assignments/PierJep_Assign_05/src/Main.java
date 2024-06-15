/**
 * Name: Jephte Pierre
 * Date: April 11, 2024
 * This program simulates cars crossing a bridge from both directions using semaphores.
 * Cars from one direction are allowed to cross the bridge only when there are no cars from
 * the opposite direction on the bridge.
 */

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Abstract class representing a car.
 */
abstract class Car implements Runnable {
    protected String name;
    protected Bridge bridge;

    /**
     * Constructor for Car class.
     *
     * @param name   The name of the car.
     * @param bridge The bridge the car will cross.
     */
    public Car(String name, Bridge bridge) {
        this.name = name;
        this.bridge = bridge;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int iterations = 0;
        while (iterations < 10) {
            try {
                Thread.sleep(rand.nextInt(100)); // Random sleep time
                bridge.crossBridge(this);
                Thread.sleep(rand.nextInt(400)); // Crossing time
                bridge.leaveBridge(this);
                iterations++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Class representing a car bound for Bishop's direction.
 */
class BishopsBoundCar extends Car {
    public BishopsBoundCar(String name, Bridge bridge) {
        super(name, bridge);
    }
}

/**
 * Class representing a car bound for Lion's direction.
 */
class LionsBoundCar extends Car {
    public LionsBoundCar(String name, Bridge bridge) {
        super(name, bridge);
    }
}

/**
 * Class representing a bridge.
 */
class Bridge {
    private final Car[] carsOnBridge;
    private final Semaphore bishopsBoundCarsSemaphore;
    private final Semaphore lionsBoundCarsSemaphore;

    /**
     * Constructor for Bridge class.
     *
     * @param maxCars Maximum number of cars allowed on the bridge at a time.
     */
    public Bridge(int maxCars) {
        carsOnBridge = new Car[maxCars];
        bishopsBoundCarsSemaphore = new Semaphore(maxCars);
        lionsBoundCarsSemaphore = new Semaphore(maxCars);
    }

    /**
     * Method to add a car to the bridge.
     *
     * @param car The car to be added.
     */
    public synchronized void addCar(Car car) {
        System.out.println(car.name + " is added to the bridge.");
        for (int i = 0; i < carsOnBridge.length; i++) {
            if (carsOnBridge[i] == null) {
                carsOnBridge[i] = car;
                break;
            }
        }
    }

    /**
     * Method to remove a car from the bridge.
     *
     * @param car The car to be removed.
     */
    public synchronized void removeCar(Car car) {
        System.out.println(car.name + " is removed from the bridge.");
        for (int i = 0; i < carsOnBridge.length; i++) {
            if (carsOnBridge[i] == car) {
                carsOnBridge[i] = null;
                break;
            }
        }
    }

    /**
     * Method to print cars currently on the bridge.
     */
    public synchronized void printCarsOnBridge() {
        System.out.println("Cars on bridge:");
        for (Car car : carsOnBridge) {
            if (car != null) {
                System.out.println(car.name);
            }
        }
        System.out.println("#------------------------------------------------------------------#");
    }

    /**
     * Method for a car to cross the bridge.
     *
     * @param car The car crossing the bridge.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void crossBridge(Car car) throws InterruptedException {
        if (car instanceof BishopsBoundCar) {
            bishopsBoundCarsSemaphore.acquire();
        } else {
            lionsBoundCarsSemaphore.acquire();
        }
        addCar(car);
        if (carsOnBridge.length == 1) {
            System.out.println("No cars from the opposite direction. " + car.name + " can proceed.");
            return;
        }
        Car firstCar = carsOnBridge[0];
        if ((car instanceof BishopsBoundCar && firstCar instanceof LionsBoundCar)
                || (car instanceof LionsBoundCar && firstCar instanceof BishopsBoundCar)) {
            System.out.println("Cars from the opposite direction are present. " + car.name + " waits.");
        } else {
            System.out.println(car.name + " can proceed.");
        }
    }

    /**
     * Method for a car to leave the bridge.
     *
     * @param car The car leaving the bridge.
     */
    public void leaveBridge(Car car) {
        removeCar(car);
        if (carsOnBridge.length == 0) {
            if (car instanceof BishopsBoundCar) {
                lionsBoundCarsSemaphore.release();
            } else {
                bishopsBoundCarsSemaphore.release();
            }
        }
        printCarsOnBridge(); // Print cars on the bridge after a car leaves
    }
}

/**
 * Main class to run the simulation.
 */

public class Main {
    public static void main(String[] args) {
        Bridge bridge = new Bridge(10);

        for (int i = 1; i <= 3; i++) {
            BishopsBoundCar car = new BishopsBoundCar("Bishop's Car " + i, bridge);
            Thread thread = new Thread(car);
            thread.start();
        }

        for (int i = 1; i <= 2; i++) {
            LionsBoundCar car = new LionsBoundCar("Lion's Car " + i, bridge);
            Thread thread = new Thread(car);
            thread.start();
        }
    }
}

