package com.borovoi.dmitrii.rpg.model.characteristics;

import lombok.Data;

/**
 * Created by dimas on 12/2/2015.
 */
@Data
public abstract class Characteristic {
    CharacteristicEnum title;
    int value;

    public Characteristic(int value, CharacteristicEnum title) {
        this.value = value;
        this.title = title;
    }

    public int getExtraDamage() {
        return 0;
    }

    public int getExtraHitpoints() {
        return 0;
    }

    public int getExtraManapoints() {
        return 0;
    }

    public int getExtraActionpoints() {
        return 0;
    }
}
