package com.company;

import java.util.Date;

/**
 * Created by dimas on 12/15/2015.
 */
//��� �� �������, ��� � ������������, �� � �������������� ��������������� ������ ���� � ������. ��������� ��� ��
//Counter: 10
//Elapsed time: 1100 ms + (����������� ���������� �� ������� � ������� +-10ms)
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final int clickNumber = 10;
        Date before = new Date();
        Thread t1 = new Thread(new CounterThread(counter, clickNumber));//������� ������ t1 ������ Thread/����� ��������� ��� ������ ����������� ��������� Runnable
        t1.start();//������ ����� ������ ����� ������, ������� �������� � ������ ������ run
        t1.join();//������������� ������� ����� main �� ��� ��� ���� �� ���������� ����� t1
        Date after = new Date();
        System.out.println("Counter: " + counter.count);
        System.out.println("Elapsed time: " + (after.getTime() - before.getTime()) + " ms");
    }
}
