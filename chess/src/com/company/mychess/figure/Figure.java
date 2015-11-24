package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Move;

/**
 * Created by teacher on 23.11.2015.
 */
public abstract class Figure implements Move {
    protected int x;//текущее положение фигуры. Protected - значит наследники могут обращатьс€ напр€мую без геттеров
    protected int y;//текущее положение фигуры
    protected FigureType figureType;//черные или белые
    protected boolean isInitPosition = true;//флаг, начальна€ ли позици€ у фигуры или ей уже походили. Ќужен пешкам или дл€ рокировки
    protected boolean isKilled = false;//флаг, ставим в true после того как фигуру съед€т

    // онструктор, который об€заны выполнить все наследники. Ѕез начальной позиции и цвета шахматна€ фигура не имеет смысла
    public Figure(int x, int y, FigureType figureType) {
        this.x = x;
        this.y = y;
        this.figureType = figureType;
    }

    //ќставлены только геттеры, чтобы нельз€ было изменить позицию фигуры в течении игры не через консутруктор или в обход метода move
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public boolean isInitPosition() {
        return isInitPosition;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setIsKilled(boolean isKilled) {
        this.isKilled = isKilled;
    }
}
