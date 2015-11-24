package com.company.mychess;

import com.company.mychess.figure.Bishop;
import com.company.mychess.figure.Figure;
import com.company.mychess.figure.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacher on 23.11.2015.
 * Ось OY направлена снизу вверху, а ось OX слева направо. Начало (1,1) находится снизу слева. Шахматная доска 8*8. Нумерация клеток с единицы. Белые фигуры всегда снизу, черные сверху.
 */
public class Chess {

    private List whites;
    private List blacks;
    private FigureType currentTurn = FigureType.WHITE;//Чей ход. Начинают белые

    //    единственный конструктор наших шахмат - единственный легальный способ создать их
    public Chess() {
        whites = new ArrayList();
        blacks = new ArrayList();
//Начальное положение всех шахмат задается нами разрботчиками, поэтому здесь проверять корректность поставновки нет необходимости. Однако все дальнейшие ходы необходимо проверять на корректность
        Pawn pawn1 = new Pawn(1, 2, FigureType.WHITE);
        Pawn pawn2 = new Pawn(2, 2, FigureType.WHITE);
        Pawn pawn3 = new Pawn(3, 2, FigureType.WHITE);
//и тд 8 штук
        Bishop bishop1 = new Bishop(1, 3, FigureType.WHITE);
        Bishop bishop2 = new Bishop(1, 6, FigureType.WHITE);
//        отметьте что List не запрещает наличия дупликатов, лучше в данном случае использовать Set, но в рамках урока можно оставить и List
        whites.add(pawn1);
        whites.add(pawn2);
        whites.add(pawn3);
        whites.add(bishop1);
        whites.add(bishop2);
    }

    //   открытый метод нашего класса, Пользователь взаимодействует с игрой через него
    public void turn() {
//        1. пользователь выбирает фигуру.
//        2. игра проверяет, что выбрана фигура нужного цвета
//        3. пользователь выбирает новое положение
//        4. игра проверяет можно ли туда походить
//        5. игра проверяет есть ли там фигура, если есть, она становится съеденной
//        6. игра проверяет образовался ли шах
//        7. игра проверяет образовался ли мат
//        8. игра завершает ход и передает возможность походить другому игроку
    }

    //   открытый метод нашего класса, Пользователь взаимодействует с игрой через него
    public Figure pickFigure(int x, int y) {
//        TODO: логика выбора фигуры игроком. Только после того как он выберет фигуру можно походить
        return null;
    }

//    все методы ниже приватные. Не даем соблазн другому разработику воспользоваться методами не предназначенными для вызова извне.
    private void validateNewPosition() {
//        проверить что новое положение не выходит за рамки доски
//        проверить что новое положение не занято своей фигурой
    }

    private void makeMove(Figure fg, int y, int x) {
// перемещаем выбранную фигуру в новое положение, задавая фигуре новые координаты
    }

    private void isCheck() {
//        TODO: образовался ли шах после очередного хода
    }

    private void isCheckmate() {
//        TODO: образовался ли мат после очередного хода
    }
}
