package com.company;

/**
 * Created by dimas on 12/15/2015.
 */
public class CounterThread implements Runnable {//����� ��������� ��������� Runnable, ����� ����� ���� ��� �������� ������ Thread

//    �������� ����� ��������� ��������� � ������������ �������� �������� ����� �����������
    private final Counter counter;
    private final int clickNumber;

    public CounterThread(Counter counter, int clickNumber) {
        this.counter = counter;
        this.clickNumber = clickNumber;
    }

//    ����� ������� �����, ������������ �������� ���� ������ �������� ������. �������� ����� � ������ ������ � ����� � �����
    @Override
    public void run() {
        while (counter.count < clickNumber) {//��������� �����-�� �������� ���� ���� �� ��������� ����
            System.err.println("Thread (1): " + Thread.currentThread().getName());
            sleep(10);//��������� �����-�� ��������� ������������ ������������� 10 ��. ����� ����� ��������� ����� ��������������� � �� ������� ��� ������ ����� �������������
            counter.inc();//����� ���������� ���������������� ��������. ��� �������� �� ����� ���� ����� �� �������� ����������, ������ ��� ��� �� ���: ���������� ������� �� ������� � ������������. �� � ����������� ������� ��� �������� ��� ������������� ����������
            System.err.println("Thread (2): " + Thread.currentThread().getName());
            sleep(100);//����� ��������� �����-�� ������ ������������ � 100 ��
        }
    }

//    ��������� ����� ��� ��������
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
