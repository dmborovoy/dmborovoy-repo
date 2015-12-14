package com.borovoi.dmitrii.rpg.model.characteristics;

/**
 * Created by dimas on 12/2/2015.
 */
public class Intelligence extends Characteristic {
    private final int extraManapoints = 10;

    @Override
    public int getExtraManapoints() {
        return extraManapoints * value;
    }

    public Intelligence(int value) {
        super(value, CharacteristicEnum.INTELLIGENCE);
    }
}
