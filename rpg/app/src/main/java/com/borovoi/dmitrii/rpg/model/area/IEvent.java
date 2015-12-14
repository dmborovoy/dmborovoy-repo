package com.borovoi.dmitrii.rpg.model.area;

import com.borovoi.dmitrii.rpg.model.units.Player;

/**
 * Created by dimas on 12/7/2015.
 */
public interface IEvent {

    void eventHandler(Player player, Event event);
}
