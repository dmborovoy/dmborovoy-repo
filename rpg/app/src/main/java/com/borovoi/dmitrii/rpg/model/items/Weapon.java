package com.borovoi.dmitrii.rpg.model.items;

import com.borovoi.dmitrii.rpg.model.characteristics.Characteristic;

import java.util.Set;

/**
 * Created by dimas on 12/2/2015.
 */
public class Weapon extends Item {

    public Weapon(int id, String title, int cost) {
        super(id, title, cost, ItemEnum.WEAPON, null);
    }

    public Weapon(int id, String title, int cost, Set<Characteristic> characteristics) {
        super(id, title, cost, ItemEnum.WEAPON, characteristics);
    }
}
