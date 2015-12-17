package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//Вариант работы однопоточной программы. Используем ее для эталонного поведения и замеров времени исполнения
//Counter: 10
//Elapsed time: 1100 ms + (погрешность меняющаяся от запуска к запуску +-10ms)
public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        while (counter.count < clickNumber) {
            sleep(10);
            counter.inc();
            sleep(100);
        }
        Date after = new Date();
        System.out.println("Counter: " + counter.count);
        System.out.println("Elapsed time: " + (after.getTime() - before.getTime()) + " ms");
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
