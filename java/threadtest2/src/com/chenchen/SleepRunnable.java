package com.chenchen;

public class SleepRunnable implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("I am begin to sleep until interrupted ...");
                Thread.sleep(100000);
            }

        } catch (InterruptedException e) {
            System.out.println(String.format("Catch interrupted exception: %s, thread exit.", e));
        }

    }
}
