package com.company;

import com.company.mychess.Chess;
import com.company.mychess.FigureType;
import com.company.mychess.figure.Bishop;
import com.company.mychess.figure.Figure;
import com.company.mychess.figure.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Game {


    final public static String SSS = "CONTANTA";

    public static int method1(Bishop a) {
        a.setIsKilled(true);
        return 1;
    }


//    public static int method1(String a) {
//        a = "abc";
//        return 1;
//    }


    public static void main(String[] args) {
        Bishop bishop = new Bishop(1, 2, FigureType.BLACK);
        System.out.print(bishop.isKilled());
        method1(bishop);
        System.out.print(bishop.isKilled());
        SSS = "asdasda";


        //        String b = "ABC";
//        System.out.print(b);
//        method1(b);
//        System.out.print(b);

//
//        List list1 = new ArrayList();
//        List<Figure> list = new ArrayList<>();
//
//        Bishop bishop = new Bishop(1, 2, FigureType.BLACK);
//        Pawn pawn1 = new Pawn(1, 2, FigureType.BLACK);
//        Pawn pawn2 = new Pawn(1, 2, FigureType.WHITE);
//
//        list.add(bishop);
//        list.add(pawn1);
//        list.add(pawn2);
//
//
//        for (Figure s : list) {
//            boolean killed = s.isKilled();
//            System.out.println("Is killed: " + killed);
//        }


//        // init game
//        Figure figure = null;
//        Chess chess = new Chess();
//        chess.startGame();
//        // round 1 white
//        figure = chess.pickFigure(1, 1);//не даем игроку походить пока figure не станет не null
//        chess.makeMove(figure, 2, 2);
//        chess.endTurn();
//        // round 2 black
//        figure = chess.pickFigure(7, 7);//не даем игроку походить пока figure не станет не null
//        chess.makeMove(figure, 6, 6);
//        chess.endTurn();
//        // etc...

    }
}
