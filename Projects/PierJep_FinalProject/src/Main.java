import java.util.List;

import javax.swing.SwingUtilities;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Skeleton.SimulationInput;
import Simulation.MoveForwardStrategyTest;
import Simulation.StopStrategyTest;
import Simulation.TrafficLightSystemTest;
import Simulation.TrafficLightTest;
import Simulation.VehicleTest;


/**
 * The Main class serves as the entry point for the simulation program.
 */
public class Main {

    /**
     * The main method to start the simulation and run unit tests.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {

        // RUN UNIT TESTS
        Result result = JUnitCore.runClasses(
                MoveForwardStrategyTest.class,
                StopStrategyTest.class,
                TrafficLightTest.class,
                TrafficLightSystemTest.class,
                VehicleTest.class
        );

        // Print test results
        System.out.println("Test Results: ");

        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()){
            System.out.println("All test passed!");
        } else {
            System.out.println("Some tests failed.");
        }

        // Create simulation input
        SimulationInput input = new SimulationInput();
        // Add input parameters
        input.addInput("Time", List.of("10")); // Simulation time in seconds
        input.addInput("ActionsPerSecond", List.of("1")); // Number of actions per second

        // Create the visualization
        SimulationVisualizer visualizer = new SimulationVisualizer(input);


        // Call the run method of Matrix class and pass the simulation input
        Matrix.run(input, visualizer);
    }
}
