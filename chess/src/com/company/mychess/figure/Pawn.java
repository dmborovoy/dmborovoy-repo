package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

/**
 * Created by teacher on 23.11.2015.
 * �����
 */
public class Pawn extends Figure implements Move {

    public Pawn(int x, int y, FigureType type) {
        super(x, y, type);
    }

    //TODO: �������� ����������� ����� �� ����������� ������ ������ �� ���� ��� ��� ������
    @Override
    public void move(int x, int y) throws Exception {
        if (this.figureType == FigureType.BLACK) {//����� ��������� ������ ����
            if (this.y - y > 0) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;//��� ������ �� ���� ��������� ����, ���������, ��� ������� ��� ��������
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        } else {//����� ��������� ������ �����
            if (this.y - y < 0) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        }
    }
}
