package Simulation;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Test {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                MoveForwardStrategyTest.class,
                StopStrategyTest.class,
                TrafficLightTest.class,
                TrafficLightSystemTest.class,
                VehicleTest.class
        );

        System.out.println("Test Results: ");

        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()){
            System.out.println("All test passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
