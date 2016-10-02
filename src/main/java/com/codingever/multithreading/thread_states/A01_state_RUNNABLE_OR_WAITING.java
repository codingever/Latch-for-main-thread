package com.codingever.multithreading.thread_states;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Created by andrii on 02.10.16.
 */
public class A01_state_RUNNABLE_OR_WAITING {
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
        // can return RUNNABLE or WAITING, because main thread can catch other
        // thread before execution of line 18
        System.out.println(thread.getState());
    }
}
