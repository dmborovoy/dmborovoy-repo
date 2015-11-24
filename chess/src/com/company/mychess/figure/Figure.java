package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Move;

/**
 * Created by teacher on 23.11.2015.
 */
public abstract class Figure implements Move {
    protected int x;//������� ��������� ������. Protected - ������ ���������� ����� ���������� �������� ��� ��������
    protected int y;//������� ��������� ������
    protected FigureType figureType;//������ ��� �����
    protected boolean isInitPosition = true;//����, ��������� �� ������� � ������ ��� �� ��� ��������. ����� ������ ��� ��� ���������

    //�����������, ������� ������� ��������� ��� ����������. ��� ��������� ������� � ����� ��������� ������ �� ����� ������
    public Figure(int x, int y, FigureType figureType) {
        this.x = x;
        this.y = y;
        this.figureType = figureType;
    }

    //��������� ������ �������, ����� ������ ���� �������� ������� ������ � ������� ���� �� ����� ������������ ��� � ����� ������ move
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FigureType getFigureType() {
        return figureType;
    }
}
