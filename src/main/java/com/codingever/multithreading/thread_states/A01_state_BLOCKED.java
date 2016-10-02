package com.codingever.multithreading.thread_states;

import java.util.concurrent.CountDownLatch;

/**
 * Created by andrii on 02.10.16.
 */
public class A01_state_BLOCKED {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (lock){
                latch.countDown();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        latch.await();
        // should return BLOCKED
        synchronized (lock) {
            lock.notify();
            System.out.println(thread.getState());
        }
    }
}
