package com.borovoi.dmitrii.rpg.model.characteristics;

/**
 * Created by dimas on 12/2/2015.
 */
public class Strength extends Characteristic {

    private final int extraDamage = 10;
    private final int extaHitpoints = 10;

    @Override
    public int getExtraDamage() {
        return extraDamage * value;
    }

    @Override
    public int getExtraHitpoints() {
        return extaHitpoints * value;
    }

    public Strength(int value) {
        super(value, CharacteristicEnum.STRENGTH);
    }
}
