package com.company;

/**
 * Created by dimas on 12/15/2015.
 */
public class CounterThread implements Runnable {//Класс реализует интерфейс Runnable, чтобы можно было его передать классу Thread

//    передаем класс хранитель счетчиков и максимальное значение счетчика через конструктор
    private final Counter counter;
    private final int clickNumber;

    public CounterThread(Counter counter, int clickNumber) {
        this.counter = counter;
        this.clickNumber = clickNumber;
    }

//    Самый главный метод, определяющий жизненый цикл нашего будущего потока. Начнется поток в начале метода и умрет в конце
    @Override
    public void run() {
        while (counter.count < clickNumber) {//выполняем какое-то полезное дело пока не сработает флаг
            System.err.println("Thread (1): " + Thread.currentThread().getName());
            sleep(10);//имитируем какую-то небольшую деятельность длительностью 10 мс. Нужно чтобы сработала магия многопоточности и мы увидели как потоки могут конкурировать
            counter.inc();//здесь просиходит инкреминирование счетчика. Эта операция на самом деле также не является безопасной, потмоу что тут их две: увеличение значние на единицу и присваивание. Но в большинстве случаев оно проходит без нежелательных артефактов
            System.err.println("Thread (2): " + Thread.currentThread().getName());
            sleep(100);//снова имитируем какую-то бурную деятельность в 100 мс
        }
    }

//    приватный метод для удобства
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
