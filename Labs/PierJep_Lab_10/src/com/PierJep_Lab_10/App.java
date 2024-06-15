/**
 * Name: Jephte Pierre
 * Date: March 29, 2024
 * * Description: In this program, we’ll practice multithreading. Using Semaphores for synchronization,
 * implement a multithreaded cook that performs the following recipe, with each task being contained
 * in a single Thread:.
 */

package com.PierJep_Lab_10;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        Semaphore[][] semaphores = new Semaphore[10][1];
        Semaphore[] dependencies = new Semaphore[10];

        for (int i = 0; i < semaphores.length; i++) {
            for (int j = 0; j < semaphores[i].length; j++) {
                semaphores[i][0] = new Semaphore(0);
            }
            // dependencies[i] = i == 0 ? semaphores[i][0] : new Semaphore(0);
            dependencies[i] = (i == 0 || i == 1 || i == 2 || i == 4 || i == 7) ? semaphores[i][0] : new Semaphore(0);
        }

        Thread[] threads = new Thread[9];
        threads[0] = new Thread(new Cook("Cut onions", semaphores, new Semaphore[]{}));
        threads[1] = new Thread(new Cook("Mince meat", semaphores, new Semaphore[]{}));
        threads[2] = new Thread(new Cook("Slice aubergines", semaphores, new Semaphore[]{}));
        threads[3] = new Thread(new Cook("Finished Bechamel", semaphores, new Semaphore[]{}));
        threads[4] = new Thread(new Cook("Turn on oven", semaphores, new Semaphore[]{}));

        // Start tasks 1, 2, 3, and 5
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }

        // Wait for tasks 1, 2, 3, and 5 to complete
        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }

        // Start tasks 4, 6, 7, 8, and 9
        threads[5] = new Thread(new Cook("Make sauce", semaphores, new Semaphore[]{semaphores[0][0], semaphores[1][0]}));
        threads[6] = new Thread(new Cook("Layout the layers", semaphores, new Semaphore[]{semaphores[2][0], semaphores[5][0]})); // Depends on Task 4 and Task 5
        threads[7] = new Thread(new Cook("Put Bechamel and Cheese", semaphores, new Semaphore[]{semaphores[4][0], semaphores[5][0]}));
        threads[8] = new Thread(new Cook("Cook", semaphores, new Semaphore[]{semaphores[6][0], semaphores[7][0]}));

        for (int i = 5; i < 9; i++) {
            threads[i].start();
        }

        // Wait for tasks 4, 6, 7, 8, and 9 to complete
        for (int i = 5; i < 9; i++) {
            threads[i].join();
        }
    }
}