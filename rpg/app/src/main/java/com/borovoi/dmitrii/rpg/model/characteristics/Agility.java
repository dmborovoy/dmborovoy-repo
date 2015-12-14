package com.borovoi.dmitrii.rpg.model.characteristics;

/**
 * Created by dimas on 12/2/2015.
 */
public class Agility extends Characteristic {
    private final int extraActionpoints = 10;

    @Override
    public int getExtraActionpoints() {
        return extraActionpoints * value;
    }

    public Agility(int value) {
        super(value, CharacteristicEnum.AGILITY);
    }
}
