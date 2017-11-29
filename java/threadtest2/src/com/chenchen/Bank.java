package com.chenchen;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Lock lock;
    private Condition bigThanEight;
    private int count;

    Bank(int count) {
        this.lock = new ReentrantLock();
        bigThanEight = lock.newCondition();
        this.count = count;
    }

    /**
     * @param mills millisecond
     */
    public void sleep(int mills) {
        lock.lock();
        System.out.println("Sleep catch the lock");
        try {
            while (count < 8) {
                bigThanEight.await();
                System.out.println("Await return");
            }

            System.out.println("Do something in race condition");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Sleep release the lock");
        }
    }

    public void tryLock()
    {
        System.out.println("Try lock...");
        lock.lock();

        System.out.println("TryLock catch the lock");
        System.out.println("Do something in race condition");

        lock.unlock();
        System.out.println("TryLock release the lock");
    }

    public void addCount(int n) {
        lock.lock();
        System.out.println("AddCount catch the lock");
        System.out.println("Do something in race condition");
        try {
            count += n;
            bigThanEight.signalAll();
            System.out.println("Signal to all");
            Thread.sleep(5000);
        }catch(InterruptedException e){

        }
        finally {

            lock.unlock();
            System.out.println("AddCount release the lock");
        }

    }
}
