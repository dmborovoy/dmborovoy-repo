package com.borovoi.dmitrii.rpg.model.area;

/**
 * Created by dimas on 12/7/2015.
 */
public enum AreaEnum {
    HIDDEN(-1),
    ROCK(0),
    ABYSS(0),
    SEA(0),
    ROAD(1),
    GRASS(2),
    ICE(2),
    SNOW(3),
    FOREST(3),
    RIVER(3),
    SWAMP(4);

    AreaEnum(int actions) {
        this.actionPoints = actions;
    }

    private final int actionPoints;

    public int getActionPoints() {
        return actionPoints;
    }
}
