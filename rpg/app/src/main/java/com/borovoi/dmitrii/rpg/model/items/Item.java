package com.borovoi.dmitrii.rpg.model.items;

import com.borovoi.dmitrii.rpg.model.characteristics.Characteristic;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * Created by dimas on 12/2/2015.
 */
@Data
public abstract class Item {
    int id;
    String title;
    int cost;
    ItemEnum itemType;
    Set<Characteristic> characteristics = new HashSet<>();

    public Item(int id, String title, int cost, ItemEnum itemType, Set<Characteristic> characteristics) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.itemType = itemType;
        this.characteristics = characteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

}
