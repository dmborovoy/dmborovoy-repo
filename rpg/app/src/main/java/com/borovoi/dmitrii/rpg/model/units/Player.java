package com.borovoi.dmitrii.rpg.model.units;

import com.borovoi.dmitrii.rpg.model.characteristics.Agility;
import com.borovoi.dmitrii.rpg.model.characteristics.Characteristic;
import com.borovoi.dmitrii.rpg.model.characteristics.Intelligence;
import com.borovoi.dmitrii.rpg.model.items.Armor;
import com.borovoi.dmitrii.rpg.model.items.Helmet;
import com.borovoi.dmitrii.rpg.model.items.Item;
import com.borovoi.dmitrii.rpg.model.characteristics.Strength;
import com.borovoi.dmitrii.rpg.model.items.Ring;
import com.borovoi.dmitrii.rpg.model.items.Shield;
import com.borovoi.dmitrii.rpg.model.items.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dimas on 12/2/2015.
 */
public class Player extends Unit {
    int experience;
    List<Strength> strengths;
    List<Agility> agilities;
    List<Intelligence> intelligences;
    Weapon leftHand;
    Shield rightHand;
    Ring ringLeft;
    Ring ringRight;
    Armor body;
    Helmet head;
    Set<Item> items = new HashSet<>();
    Set<Item> bag = new HashSet<>();


    public Player(int hitPoints, int actionPoints, int manaPoints, int damage, int level) {
        super(hitPoints, actionPoints, manaPoints, damage, level, UnitEnum.PLAYER);
    }

    public Player() {
        super(UnitEnum.PLAYER);
    }

    public void wearRingRight(Ring ring) {
        if(ringRight!=null) {
            bag.add(ringRight);
            ringRight = null;
        }
        ringRight = ring;
        items.add(ringRight);
    }

    public void unWearRingRight() {
        items.remove(ringRight);
        ringRight = null;
    }

    public int getTotalDamage() {
        int totDmg = damage;
        for (Item item : items) {
            if (item != null) {
                Set<Characteristic> characteristics = item.getCharacteristics();
                for (Characteristic characteristic : characteristics) {
                    totDmg += characteristic.getExtraDamage();
                }
            }
        }
        return totDmg;
    }

}
