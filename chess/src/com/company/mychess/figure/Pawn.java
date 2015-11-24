package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

/**
 * Created by teacher on 23.11.2015.
 * Пешка
 */
public class Pawn extends Figure implements Move {

    public Pawn(int x, int y, FigureType type) {
        super(x, y, type);
    }

    //TODO: Доделать ограничение пешке на возможность ходить только на одну или две клетки
    @Override
    public void move(int x, int y) throws Exception {
        if (this.figureType == FigureType.BLACK) {//могут двигаться только вниз
            if (this.y - y > 0) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;//при первом же ходе обновляем флаг, запоминая, что фигурой уже походили
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        } else {//могут двигаться только вверх
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
