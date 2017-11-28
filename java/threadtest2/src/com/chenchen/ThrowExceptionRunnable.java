package com.chenchen;

public class ThrowExceptionRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(3 / 2);
        System.out.println(3 / 0);    //throw ArithmeticException
        System.out.println(3 / 1);
    }
}
