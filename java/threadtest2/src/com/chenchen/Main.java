package com.chenchen;

public class Main {
    public static void main(String args[]) {
        testAlwaysRunnable();
    }


    /**
     * @note 处于sleep状态的线程如果被打断，则会抛出InterrupedException
     */
    private static void testSleepRunnable() {
        Runnable r = new SleepRunnable();
        Thread t = new Thread(r);
        t.start();

        try {
            System.out.println("Main thread sleep 2s");
            Thread.sleep(2000);

            System.out.println("Main thread send interrupted signal ...");
            t.interrupt();

            //t.join();
            System.out.println("Main thread exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @note 处于运行状态的线程，不会抛出ThreadInterupted Exception
     */
    private static void testAlwaysRunnable() {
        Runnable r = new AlwaysRunnable();
        Thread t = new Thread(r);
        t.start();

        try {
            System.out.println("Main thread sleep 2s");
            Thread.sleep(2000);

            System.out.println("Main thread send interrupted signal ...");
            t.interrupt();

            t.join();
            System.out.println("Main thread exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
