package com.example.spacetrader;

import com.example.spacetrader.entity.Event;
import com.example.spacetrader.entity.Good;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.TechLevel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit Tests for buy method in Market.
 * @author Ida Wang
 * @version 1.0
 */
public class MarketBuyUnitTest {
    private Player player = new Player();
    private SolarSystem solarSystem = new SolarSystem(
            Planet.A, TechLevel.MD, Resource.NR, Event.NONE);
    private Market market = new Market(player, solarSystem);

    @Test
    public void notEnoughCredits() {
        player.setCredits(0);
        assertEquals(market.buy(Good.FOOD, 1),
                "You do not have enough credits to buy that!");
    }

    @Test
    public void notEnoughSpace() {
        player.setTotalGoods(15);
        assertEquals(market.buy(Good.WATER, 1),
                "You cannot hold that many goods!");
    }

    @Test
    public void noMoreGoods() {
        market.getInventory().put(Good.WATER, 0);
        assertEquals(market.buy(Good.WATER, 1),
                "There is/are no "
                        + Good.WATER.getName()
                        + " left on this planet!");
    }

    @Test
    public void completePurchase() {
        market.getInventory().put(Good.WATER, 1);
        assertEquals(market.buy(Good.WATER, 1),
                "Purchase complete");
    }
}
