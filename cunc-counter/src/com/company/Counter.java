package com.company;

/**
 * Created by dimas on 12/1/2015.
 */
//Очень простой класс, который имеет единственный метод, инкренимирующий счетчик
public class Counter {
    public int count = 0;//сделаем его открытым для простоты

    public void inc() {
        this.count++;
    }
}