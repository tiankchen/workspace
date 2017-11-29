package com.chenchen;

public class ConditionRunnable implements Runnable {
    Bank bank;
int sec;
    ConditionRunnable(Bank b, int sec) {
        super();
        this.bank = b;
        this.sec = sec;
    }

    @Override
    public void run() {
        bank.sleep(sec);
    }
}
