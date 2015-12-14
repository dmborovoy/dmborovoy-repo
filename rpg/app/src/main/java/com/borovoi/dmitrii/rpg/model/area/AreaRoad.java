package com.borovoi.dmitrii.rpg.model.area;

/**
 * Created by dimas on 12/7/2015.
 */
public class AreaRoad extends Area {

    public AreaRoad() {
        super(AreaEnum.ROAD);
    }

    @Override
    public String toView() {
        return "1";
    }
}
