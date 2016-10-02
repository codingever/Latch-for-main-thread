package com.codingever.multithreading.thread_states;

import java.util.concurrent.CountDownLatch;

/**
 * Created by andrii on 02.10.16.
 */
public class A01_state_RUNNABLE {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            latch.countDown();
//            while (true);
            for(;;){}
        });
        thread.start();
        latch.await();
        // should return RUNNABLE
        System.out.println(thread.getState());
    }
}
