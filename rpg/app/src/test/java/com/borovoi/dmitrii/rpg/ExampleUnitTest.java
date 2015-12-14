package com.borovoi.dmitrii.rpg;

import com.borovoi.dmitrii.rpg.model.area.AreaForest;
import com.borovoi.dmitrii.rpg.model.area.AreaRoad;
import com.borovoi.dmitrii.rpg.model.area.AreaRock;
import com.borovoi.dmitrii.rpg.model.area.AreaSwamp;
import com.borovoi.dmitrii.rpg.model.area.Board;
import com.borovoi.dmitrii.rpg.model.characteristics.Agility;
import com.borovoi.dmitrii.rpg.model.characteristics.Strength;
import com.borovoi.dmitrii.rpg.model.items.Ring;
import com.borovoi.dmitrii.rpg.model.units.Player;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Strength strength1 = new Strength(1);
        Agility agility3 = new Agility(3);
        Ring ringOfStrength = new Ring(1001, "Ring of strength and agility", 15);
        ringOfStrength.getCharacteristics().add(strength1);
        ringOfStrength.getCharacteristics().add(agility3);
        System.out.println(ringOfStrength);
        Player player = new Player();
        player.wearRingRight(ringOfStrength);
        int totalDamage = player.getTotalDamage();
        System.out.println(totalDamage);
        player.unWearRingRight();
        totalDamage = player.getTotalDamage();
        System.out.println(totalDamage);
//        Board.BoardBuilder builder = Board.builder();
//        builder.
    }

    @Test
    public void testName() throws Exception {
        Board board = new Board(15, 20);
        board.fill(new AreaRock());
        board.addCol(3, new AreaRoad());
        board.addRow(5, new AreaSwamp());
        board.addBorder(new AreaForest());
        Player player = new Player();
        board.start(0, 0, player);
        board.print();
        board.move(0, 1);
        board.print();
        board.move(7, 7);
        board.print();
    }
}