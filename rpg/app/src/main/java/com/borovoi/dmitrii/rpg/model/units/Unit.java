package com.borovoi.dmitrii.rpg.model.units;

import lombok.Data;

/**
 * Created by dimas on 12/2/2015.
 */
@Data
public class Unit {
    int hitPoints;
    int actionPoints;
    int manaPoints;
    int damage;
    int level;
    UnitEnum unitType;
    int viewRange = 3;

//    int x;
//    int y;

    public Unit(int hitPoints, int actionPoints, int manaPoints, int damage, int level, UnitEnum unitType) {
        this.hitPoints = hitPoints;
        this.actionPoints = actionPoints;
        this.manaPoints = manaPoints;
        this.damage = damage;
        this.level = level;
        this.unitType = unitType;
    }

    public Unit(UnitEnum unitType) {
        this.unitType = unitType;
    }

}


