package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//используем безопаный и быстрый вариант класса CounterThreadSyncFast. Максимально уменьшив область действия блока sync мы и обезопасили себя от конкурентного доступа к данным и увеличили скорость работы в 3-4 раза
//Counter: 10
//Elapsed time: 341 ms
public class Main5 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        Thread t1 = new Thread(new CounterThreadSyncFast(counter, clickNumber));
        Thread t2 = new Thread(new CounterThreadSyncFast(counter, clickNumber));
        Thread t3 = new Thread(new CounterThreadSyncFast(counter, clickNumber));
        Thread t4 = new Thread(new CounterThreadSyncFast(counter, clickNumber));
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
