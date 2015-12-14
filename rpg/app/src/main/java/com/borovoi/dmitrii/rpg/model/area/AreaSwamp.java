package com.borovoi.dmitrii.rpg.model.area;

/**
 * Created by dimas on 12/7/2015.
 */
public class AreaSwamp extends Area {

    public AreaSwamp() {
        super(AreaEnum.SWAMP);
    }

    @Override
    public String toView() {
        return String.valueOf(getAreaType().getActionPoints());
    }
}
