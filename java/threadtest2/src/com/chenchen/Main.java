package com.chenchen;


import com.chenchen.Bank.Level;

import static com.chenchen.Bank.Level.ERROR;


public class Main {
    public static void main(String args[]) {
        new Main().testCondition();
        int i = ERROR.ordinal();
        Level l =  Level.values()[1];
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
        Thread t = new Thread(new ThrowExceptionRunnable());
        t.start();
    }


    class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("==Exception: " + e.getMessage());
        }

    }

    /**
     * Test condition lock.
     * Lock.lock() 加锁，Lock.unlock() 解锁
     * Condition.await() 释放锁，等待通知
     * Condition.signalAll() 通知所有等待的线程可以尝试重新去获取锁，并且在Lock.unlock()之后，从等待的线程处继续执行
     */
    private void testCondition() {
        Bank b = new Bank(3);
        ConditionRunnable r = new ConditionRunnable(b, 5);
        Thread t = new Thread(r);
        t.start();

        try {
            Thread.sleep(1000);
            b.addCount(10);
            b.tryLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
