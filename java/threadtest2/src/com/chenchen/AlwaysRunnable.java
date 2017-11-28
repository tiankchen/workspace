package com.chenchen;

public class AlwaysRunnable implements Runnable {
    @Override
    public void run() {

        while (true) {
            int count = 0;
            for (int i = 0; i < 100000000; ++i) {
                for (int j = 0; j < 10000000; ++j) {
                    int number = 3;
                }
            }
            ++count;
            if (count == 20)
                break;

            System.out.println(String.format("%s", count));
        }
    }
}
