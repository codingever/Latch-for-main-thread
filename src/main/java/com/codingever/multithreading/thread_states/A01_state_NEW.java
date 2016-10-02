package com.codingever.multithreading.thread_states;

/**
 * Created by andrii on 02.10.16.
 */
public class A01_state_NEW {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
        });
        // should return NEW, because thread wasn't started
        System.out.println(thread.getState());
    }
}
