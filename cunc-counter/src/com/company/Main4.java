package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//используем здесь безопасный синхронизированный вариант CounterThreadSync, который обезопасил нас от конкурентного доступа к счетчику
//Результат: целостность данных сохранена, но из-за блока sync никакого ускорения опять не получилось, блок блокировал другие потоки от доступа до конца своей работы и по сути опять получили однопоточный вариант
//Counter: 10
//Elapsed time: 1100 ms + (погрешность меняющаяся от запуска к запуску +-10ms)
public class Main4 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        Thread t1 = new Thread(new CounterThreadSync(counter, clickNumber));
        Thread t2 = new Thread(new CounterThreadSync(counter, clickNumber));
        Thread t3 = new Thread(new CounterThreadSync(counter, clickNumber));
        Thread t4 = new Thread(new CounterThreadSync(counter, clickNumber));
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
