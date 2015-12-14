package com.borovoi.dmitrii.rpg.model.area;

/**
 * Created by dimas on 12/7/2015.
 */
public class AreaRock extends Area {

    public AreaRock() {
        super(AreaEnum.ROCK);
    }

    @Override
    public String toView() {
        return "R";
    }
}
