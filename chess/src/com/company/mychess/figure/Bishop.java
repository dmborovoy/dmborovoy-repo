package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

/**
 * Created by teacher on 23.11.2015.
 * Слон/Офицер
 */
public class Bishop extends Figure implements Move {

    public Bishop(int x, int y, FigureType type) {
        super(x, y, type);//вызов родительского конструктора
    }

    public void move(int x, int y) throws Exception {
        if (Math.abs(this.x - x) == Math.abs(this.y - y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }
    }
}
