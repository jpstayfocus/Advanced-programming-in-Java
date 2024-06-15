package com.PierJep_Lab_10;

import java.util.concurrent.Semaphore;

public class Cook implements Runnable {
    private final String taskName;
    private final Semaphore[][] semaphores;
    private final Semaphore[] dependencies;


    public Cook(String taskName, Semaphore[][] semaphores, Semaphore[] dependencies) {
        this.taskName = taskName;
        this.semaphores = semaphores;
        this.dependencies = dependencies;
    }

    @Override
    public void run() {
        try {
            // Wait for dependencies to be completed
            for (Semaphore dependency : dependencies) {
                dependency.acquire();
            }

            // Start executing the task
            System.out.println("Starting task: " + taskName);
            Thread.sleep((long) (Math.random() * 10000) + 2000); // Sleep for 2-11 seconds
            System.out.println("Completed task: " + taskName);

            // Signal the semaphore for the next task to start
            for (Semaphore[] semaphoreRow : semaphores) {
                for (Semaphore semaphore : semaphoreRow) {
                    semaphore.release();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
