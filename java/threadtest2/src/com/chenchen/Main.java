package com.chenchen;

public class Main {
    public static void main(String args[]) {
        new Main().testThreadException();
    }


    /**
     * @note 处于sleep状态的线程如果被打断，则会抛出InterrupedException
     */
    private void testSleepRunnable() {
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
    private void testAlwaysRunnable() {
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

    /**
     * @note try catch thread exception
     * @ref http://blog.csdn.net/u013256816/article/details/50417822
     */
    private void testThreadException() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        Thread t =  new Thread(new ThrowExceptionRunnable());
        t.start();
    }

    class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("==Exception: " + e.getMessage());
        }
    }
}
