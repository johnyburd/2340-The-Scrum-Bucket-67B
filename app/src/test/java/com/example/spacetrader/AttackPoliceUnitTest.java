package com.example.spacetrader;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Spaceship;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AttackPoliceUnitTest {
    private final Player player = new Player();
    @Test
    public void players_hull_was_negative() {
        player.setSpaceship(Spaceship.GNAT);
        player.getShip().setCurrHull(-1);
        player.attackPolice(System.nanoTime());
        assertEquals(0, player.getShip().getCurrHull());
    }

    @Test
    public void players_hull_is_positive() {
        player.setSpaceship(Spaceship.GNAT);
        player.getShip().setCurrHull(10000);
        player.attackPolice(System.nanoTime());
        assertTrue("Current Hull: " + player.getShip().getCurrHull(),
                player.getShip().getCurrHull() >= 0);
    }
}
