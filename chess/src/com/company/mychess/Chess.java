package com.company.mychess;

import com.company.mychess.figure.Bishop;
import com.company.mychess.figure.Figure;
import com.company.mychess.figure.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacher on 23.11.2015.
 * ��� OY ���������� ����� ������, � ��� OX ����� �������. ������ (1,1) ��������� ����� �����. ��������� ����� 8*8. ��������� ������ � �������. ����� ������ ������ �����, ������ ������.
 */
public class Chess {

    private List whites;
    private List blacks;
    private FigureType currentTurn = FigureType.WHITE;//��� ���. �������� �����

    //    ������������ ����������� ����� ������ - ������������ ��������� ������ ������� ��
    public Chess() {
        whites = new ArrayList();
        blacks = new ArrayList();
//��������� ��������� ���� ������ �������� ���� �������������, ������� ����� ��������� ������������ ����������� ��� �������������. ������ ��� ���������� ���� ���������� ��������� �� ������������
        Pawn pawn1 = new Pawn(1, 2, FigureType.WHITE);
        Pawn pawn2 = new Pawn(2, 2, FigureType.WHITE);
        Pawn pawn3 = new Pawn(3, 2, FigureType.WHITE);
//� �� 8 ����
        Bishop bishop1 = new Bishop(1, 3, FigureType.WHITE);
        Bishop bishop2 = new Bishop(1, 6, FigureType.WHITE);
//        �������� ��� List �� ��������� ������� ����������, ����� � ������ ������ ������������ Set, �� � ������ ����� ����� �������� � List
        whites.add(pawn1);
        whites.add(pawn2);
        whites.add(pawn3);
        whites.add(bishop1);
        whites.add(bishop2);
    }

    //   �������� ����� ������ ������, ������������ ��������������� � ����� ����� ����
    public void turn() {
//        1. ������������ �������� ������.
//        2. ���� ���������, ��� ������� ������ ������� �����
//        3. ������������ �������� ����� ���������
//        4. ���� ��������� ����� �� ���� ��������
//        5. ���� ��������� ���� �� ��� ������, ���� ����, ��� ���������� ���������
//        6. ���� ��������� ����������� �� ���
//        7. ���� ��������� ����������� �� ���
//        8. ���� ��������� ��� � �������� ����������� �������� ������� ������
    }

    //   �������� ����� ������ ������, ������������ ��������������� � ����� ����� ����
    public Figure pickFigure(int x, int y) {
//        TODO: ������ ������ ������ �������. ������ ����� ���� ��� �� ������� ������ ����� ��������
        return null;
    }

//    ��� ������ ���� ���������. �� ���� ������� ������� ����������� ��������������� �������� �� ���������������� ��� ������ �����.
    private void validateNewPosition() {
//        ��������� ��� ����� ��������� �� ������� �� ����� �����
//        ��������� ��� ����� ��������� �� ������ ����� �������
    }

    private void makeMove(Figure fg, int y, int x) {
// ���������� ��������� ������ � ����� ���������, ������� ������ ����� ����������
    }

    private void isCheck() {
//        TODO: ����������� �� ��� ����� ���������� ����
    }

    private void isCheckmate() {
//        TODO: ����������� �� ��� ����� ���������� ����
    }
}
