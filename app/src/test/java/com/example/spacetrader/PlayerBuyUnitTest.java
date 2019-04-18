package com.example.spacetrader;

import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit Tests for buy method in Player.
 * @author Ida Wang
 * @version 1.0
 */
public class PlayerBuyUnitTest {
    private final Player player = new Player();
    private final int cost = 40;

    /**
     * Test for buying an item without enough credits.
     */
    @Test
    public void notEnoughCredits() {
        player.setCredits(0);
        assertEquals(player.buy(Good.WATER, 1, cost),
                "You do not have enough credits to buy that!");
    }

    /**
     * Test for buying an item without enough cargo space.
     */
    @Test
    public void notEnoughSpace() {
        player.setTotalGoods(player.getShip().getCargo());
        assertEquals(player.buy(Good.WATER, 1, cost),
                "You cannot hold that many goods!");
    }

    /**
     * Test for completing a purchase with valid credits and cargo space.
     */
    @Test
    public void completePurchase() {
        assertEquals(player.buy(Good.WATER, 1, cost),
                "Purchase complete");
    }
}
