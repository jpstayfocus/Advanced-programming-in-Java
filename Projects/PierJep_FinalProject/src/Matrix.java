import java.util.List;
import java.util.concurrent.Semaphore;

import Skeleton.SimulationInput;
import Skeleton.StatisticsContainer;
import Simulation.LightColor;
import Simulation.TrafficLightSystem;
import Simulation.Vehicle;


/**
 * The Matrix class represents the main simulation engine.
 */
public class Matrix {

    /**
     * Runs the simulation using the provided input and visualizer.
     *
     * @param input The simulation input parameters.
     * @param visualizer The visualizer for the simulation.
     */
    public static void run(SimulationInput input, SimulationVisualizer visualizer) {
        // Initialize a traffic light system
        TrafficLightSystem trafficLightSystem = TrafficLightSystem.getInstance();

        // initialize semaphore with permit
        Semaphore semaphore = new Semaphore(1);

        // Create vehicles
        Vehicle car1 = new Vehicle("Car1", input, semaphore, trafficLightSystem);
        Vehicle car2 = new Vehicle("Car2", input, semaphore, trafficLightSystem);


        // Set initial positions and speeds
        car1.setPosition(0);
        car1.setSpeed(15); // Car1 moves at speed 5
        car2.setPosition(20);
        car2.setSpeed(30);// Car2 moves at speed 3


        // Crease vehicle threads
        Thread car1Thread = new Thread(car1);
        Thread car2Thread = new Thread(car2);

        // start threads
        car1Thread.start();
        car2Thread.start();


        // Run the simulation
        for (int i = 0; i < Integer.parseInt(input.getInput("Time").get(0)); i++) {
            System.out.println("\n --- Simulation Time: " + (i + 1) + " seconds ---");

            // Change traffic light color every 5 seconds
            if ((i + 1) % 5 == 0) {
                trafficLightSystem.changeLight(LightColor.RED);
            } else {
                trafficLightSystem.changeLight(LightColor.GREEN);
            }

            // Perform actions for each vehicle
            car1.performAction();
            car1.submitStatistics();
            car2.performAction();
            car2.submitStatistics();

            // Update visualization
            visualizer.updateVisualization(List.of(car1, car2), trafficLightSystem.getCurrentLightColor());


            // Wait for 1 second (actions per second)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            car1Thread.join();
            car2Thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        // Get and print statistics
        StatisticsContainer stats = StatisticsContainer.getInstance();
        stats.printStatisticsContainer();

    }
}

