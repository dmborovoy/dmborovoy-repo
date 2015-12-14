package com.borovoi.dmitrii.rpg.model.area;

import com.borovoi.dmitrii.rpg.model.items.Item;
import com.borovoi.dmitrii.rpg.model.units.Player;
import com.borovoi.dmitrii.rpg.model.units.Unit;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.Builder;

/**
 * Created by dimas on 12/7/2015.
 */

//TODO implement as builder
public class Board {

    boolean FOW = true;
    int maxX;
    int maxY;
    Area[][] cells;
    List<Unit> enemies = new ArrayList<>();
    List<Item> treasuries = new ArrayList<>();
    List<IEvent> eventList = new ArrayList<>();
    Player player;
    int playerX;
    int playerY;


    private void render() {
        int rng = player.getViewRange();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (Math.abs(playerX - i) <= rng && Math.abs(playerY - j) <= rng) {
                    cells[i][j].setHidden(false);
//                    System.out.println("i=" + i + ";j=" + j);
                }

            }
        }
    }

    public void start(int x, int y, Player player) {
        this.player = player;
        playerX = x;
        playerY = y;
        render();
    }

    public void move(int x, int y) {
        playerX = x;
        playerY = y;
        render();
        //choose eventhandler by coordinates
        for (IEvent event : eventList) {
            event.eventHandler(player, new Event());
        }
    }

    public void addEvent(int x, int y, Event area) {

    }


    public void addListener(IEvent iEvent) {//add all AreaHandlers
        eventList.add(iEvent);
    }

    public Board(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        cells = new Area[maxX][maxY];
    }

    public void add(int x, int y, Area area) {
        cells[x][y] = area.copy();
    }

    public void fill(Area area) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = area.copy();
            }
        }
    }

    public void addBorder(Area area) {
        addRow(0, area.copy());
        addRow(maxX - 1, area.copy());
        addCol(0, area.copy());
        addCol(maxY - 1, area.copy());
    }

    public void addRow(int x, Area area) {
        for (int j = 0; j < cells[x].length; j++) {
            cells[x][j] = area.copy();
        }
    }

    public void addCol(int y, Area area) {
        for (int i = 0; i < cells.length; i++) {
            cells[i][y] = area.copy();
        }
    }

    public Area getArea(int x, int y) {
        return cells[x][y].copy();
    }

    public void print() {
        print(true);
    }

    public void print(boolean showFOW) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("@");
                } else if (cells[i][j].isHidden() && showFOW) {
                    System.out.print("~");
                } else {
                    System.out.print(cells[i][j].toView());
                }
            }
            System.out.println();
        }

//        for (Area[] xx : cells) {
//            for (Area yy : xx) {
//                if (yy.isHidden() && showFOW) {
//                    System.out.print("~");
//                } else {
//                    System.out.print(yy.toView());
//                }
//            }
//            System.out.println();
//        }
//        System.out.print(yy.toView());
    }
}
