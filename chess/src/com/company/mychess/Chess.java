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

//        1. пользователь выбирает фигуру.
//        2. игра проверяет, что выбрана фигура нужного цвета
//        3. пользователь выбирает новое положение
//        4. игра проверяет можно ли туда походить
//        5. игра проверяет есть ли там фигура, если есть, она становится съеденной
//        6. игра проверяет образовался ли шах
//        7. игра проверяет образовался ли мат
//        8. игра завершает ход и передает возможность походить другому игроку

    private List<Figure> whites;
    private List<Figure> blacks;
    private FigureType currentTurn = FigureType.WHITE;//Чей ход. Начинают белые
    private Figure[][] board = new Figure[8][8];

    private final static int MIN_X = 1;
    private final static int MIN_Y = 1;
    private final static int MAX_X = 8;
    private final static int MAX_Y = 8;

    //единственный конструктор наших шахмат - единственный легальный способ создать их
    public Chess() {
    }

    public void startGame() {
        init();
    }

    //открытый метод нашего класса, Пользователь взаимодействует с игрой через него
    public void endTurn() {
        currentTurn = currentTurn == FigureType.WHITE ? currentTurn.BLACK : currentTurn.WHITE;
    }

    //открытый метод нашего класса, Пользователь взаимодействует с игрой через него
    public Figure pickFigure(int x, int y) {
        //проверяем выбранные координаты на предмет выхода за границы поля
        if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y)
            return null;
        //находим фигуру, если она там есть. Если нет то метод вернет null
        Figure figure = findByCoordinate(x, y);
        //проверяем свою ли фигуру выбрал игрок
        if (figure == null || figure.getFigureType() != currentTurn)
            return null;
        return figure;
    }

    public void makeMove(Figure fg, int y, int x) {
        try {
            int xOld = fg.getX();
            int yOld = fg.getY();
            fg.move(x, y);
            board[b2mX(xOld)][b2mY(yOld)] = null;//убираем фигуру со старого места
            board[b2mX(fg.getX())][b2mY(fg.getY())] = fg;//и ставим на новое
            Figure anotherFigure = findByCoordinate(x, y);
            //съедаем фигуру если выбранная клетка содержит фигуру и фигура другого типа
            if (anotherFigure != null && anotherFigure.getFigureType() != fg.getFigureType()) {
                anotherFigure.setIsKilled(true);
            }

        } catch (Exception e) {
            //отлавливаем некорректный ход
            e.printStackTrace();
        }
// перемещаем выбранную фигуру в новое положение, задавая фигуре новые координаты. Если это UI то координаты выбирает пользователь мышкой
    }

    private void init() {
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
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
//        помещаем все фигуры на доску, не забывая перевести координаты доски в индексы матрицы
        for (Figure figure : whites) {
            board[b2mX(figure.getX())][b2mY(figure.getY())] = figure;
        }
        for (Figure figure : blacks) {
            board[b2mX(figure.getX())][b2mY(figure.getY())] = figure;
        }
    }

    //    все методы ниже приватные. Не даем соблазн другому разработику воспользоваться методами не предназначенными для вызова извне.
    private void validateNewPosition() {
//        проверить что новое положение не выходит за рамки доски
//        проверить что новое положение не занято своей фигурой
    }

    //переводит координаты доски в координаты матрицы
    private int b2mX(int x) {
        return x - 8;
    }

    //переводит координаты доски в координаты матрицы
    private int b2mY(int y) {
        return y - 1;
    }

    //переводит координаты матрицы в координаты доски
    private int m2bX(int x) {
        return x + 8;
    }

    //переводит координаты матрицы в координаты доски
    private int m2bY(int y) {
        return y + 1;
    }

    //    не забываем перевести координаты доски в индексы матрицы
    private Figure findByCoordinate(int x, int y) {
        return board[b2mX(x)][b2mY(y)];
    }


    private void isCheck() {
//        TODO: образовался ли шах после очередного хода
    }

    private void isCheckmate() {
//        TODO: образовался ли мат после очередного хода
    }
}
