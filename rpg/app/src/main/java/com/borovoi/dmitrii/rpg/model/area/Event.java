package com.borovoi.dmitrii.rpg.model.area;

import com.borovoi.dmitrii.rpg.model.items.Item;
import com.borovoi.dmitrii.rpg.model.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimas on 12/7/2015.
 */
public class Event {

    String description;
    List<Unit> enemies = new ArrayList<>();
    List<Item> treasuries = new ArrayList<>();

}
