package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//для получения ускорения создадим несколько потоков, передавая им всем один и тот же объект counter, хранящий счетчик
//Counter: 12
//Elapsed time: 331 ms
//Результат реальное ускорение в 3-4 раза, но с непредвиденными последствиемя. Вместо того, чтобы остановить выполнение на значении счетчика 10 несколько потоков успели увеличить счетчик "нелегально"
public class Main3 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        Thread t1 = new Thread(new CounterThread(counter, clickNumber));
        Thread t2 = new Thread(new CounterThread(counter, clickNumber));
        Thread t3 = new Thread(new CounterThread(counter, clickNumber));
        Thread t4 = new Thread(new CounterThread(counter, clickNumber));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        Date after = new Date();
        System.out.println("Counter: " + counter.count);
        System.out.println("Elapsed time: " + (after.getTime() - before.getTime()) + " ms");
    }
}
