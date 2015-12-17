package com.company;

/**
 * Created by dimas on 12/15/2015.
 */
public class CounterThreadSyncFast implements Runnable {
    private final Counter counter;
    private final int clickNumber;

    public CounterThreadSyncFast(Counter counter, int clickNumber) {
        this.counter = counter;
        this.clickNumber = clickNumber;
    }

    @Override
    public void run() {
        while (true) {
            System.err.println("Thread (1): " + Thread.currentThread().getName());
            sleep(10);
            synchronized (counter) { // уменьшили максимально область работы блока sync, чтобы получить выгоду от многопоточности, иначе программа будет работать эквивалетно однопоточной
                if (counter.count >= clickNumber) {
                    break;
                }
                counter.inc();
            }
            System.err.println("Thread (2): " + Thread.currentThread().getName());
            sleep(100);
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
