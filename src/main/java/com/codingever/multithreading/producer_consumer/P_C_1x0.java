package com.codingever.multithreading.producer_consumer;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andrii on 14.10.16.
 */
public class P_C_1x0 {
    private static final Random RANDOM = new Random();
    private static List holder = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread p1 = new Thread(() -> {
            while (true) {
                synchronized (holder) {
                    if (holder.isEmpty()) {
                        int number = RANDOM.nextInt();
                        System.out.println("p1->" + number);
                        holder.add(number);
                        holder.notifyAll();
                    }
                    try {
                        holder.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        p1.start();
    }
}
