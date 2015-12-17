package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//Тот же вариант, что и однопоточный, но с использованием дополнительного потока хоть и одного. Результат тот же
//Counter: 10
//Elapsed time: 1100 ms + (погрешность меняющаяся от запуска к запуску +-10ms)
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        Thread t1 = new Thread(new CounterThread(counter, clickNumber));//создаем объект t1 класса Thread/Поток передавая ему объект реализующий интерфейс Runnable
        t1.start();//начало жизни нашего новго потока, которое начнется в начале метода run
        t1.join();//останавливаем осноной поток main до тех пор пока не отработает поток t1
        Date after = new Date();
        System.out.println("Counter: " + counter.count);
        System.out.println("Elapsed time: " + (after.getTime() - before.getTime()) + " ms");
    }
}
