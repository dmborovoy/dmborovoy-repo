package com.borovoi.dmitrii.rpg.model.area;

import lombok.Data;

/**
 * Created by dimas on 12/7/2015.
 */
@Data
public abstract class Area implements Cloneable {

    AreaEnum areaType;
    boolean hidden = true;

    public Area(AreaEnum areaType) {
        this.areaType = areaType;
    }

    public abstract String toView();

    protected Area copy(){
        try {
            return (Area)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
