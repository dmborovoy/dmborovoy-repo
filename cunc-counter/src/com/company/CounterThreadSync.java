package com.company;

/**
 * Created by dimas on 12/15/2015.
 */
public class CounterThreadSync implements Runnable {
    private final Counter counter;
    private final int clickNumber;

    public CounterThreadSync(Counter counter, int clickNumber) {
        this.counter = counter;
        this.clickNumber = clickNumber;
    }

    @Override
    public void run() {
        synchronized (counter) {//оборачиваем опасные с точки зрения многопоточности участки кода в блок syncronized. Все остальное осталось без изменений
            while (counter.count < clickNumber) {
                System.err.println("Thread (1): " + Thread.currentThread().getName());
                sleep(10);
                counter.inc();
                System.err.println("Thread (2): " + Thread.currentThread().getName());
                sleep(100);
            }
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
