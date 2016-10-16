package com.codingever.multithreading.producer_consumer;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andrii on 14.10.16.
 */
public class P_C_1x3 {
    private static final Random RANDOM = new Random();
    private static List holder = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread p1 = new Thread(() -> {
            while (true) {
                synchronized (holder) {
                    if (holder.isEmpty()) {
                        int number = RANDOM.nextInt();
                        System.out.println("p1->"+number);
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

        Thread c1 = new Thread(() -> {
            while (true) {
                synchronized (holder) {
                    if (holder.isEmpty()) {
                        try {
                            holder.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c1<-"+holder.remove(0));
                    holder.notify();
                }
            }
        });
        c1.start();

        new Thread(() -> {
            while (true) {
                synchronized (holder) {
                    if (holder.isEmpty()) {
                        try {
                            holder.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c2<-"+holder.remove(0));
                    holder.notify();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (holder) {
                    if (holder.isEmpty()) {
                        try {
                            holder.wait();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c3<-"+holder.remove(0));
                    holder.notify();
                }
            }
        }).start();
    }
}
