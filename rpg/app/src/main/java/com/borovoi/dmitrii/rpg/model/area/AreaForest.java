package com.borovoi.dmitrii.rpg.model.area;

/**
 * Created by dimas on 12/7/2015.
 */
public class AreaForest extends Area {

    public AreaForest() {
        super(AreaEnum.FOREST);
    }

    @Override
    public String toView() {
        return "F";
    }
}
