package com.codingever.multithreading.thread_states;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * Created by andrii on 02.10.16.
 */
public class A01_state_TERMINATED {

    private static CountDownLatch latch = new CountDownLatch(1);
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        // should return TERMINATED
        System.out.println(thread.getState());
    }
}
