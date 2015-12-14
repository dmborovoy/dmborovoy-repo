package com.borovoi.dmitrii.rpg.model.items;

import com.borovoi.dmitrii.rpg.model.characteristics.Characteristic;

import java.util.Set;

/**
 * Created by dimas on 12/2/2015.
 */
public class Shield extends Item {

    public Shield(int id, String title, int cost) {
        super(id, title, cost, ItemEnum.SHIELD, null);
    }

    public Shield(int id, String title, int cost, ItemEnum itemType, Set<Characteristic> characteristics) {
        super(id, title, cost, itemType, characteristics);
    }
}
