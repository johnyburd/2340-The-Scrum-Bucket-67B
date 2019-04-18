package com.example.spacetrader.entity;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Random;

/**
 * The market used to trade goods between the player and the planet they are at
 * @author Scrum Bucket
 * @version 1.0
 */
public class Market {

    private final Player player;
    private final SolarSystem planet;
    private Event event;
    //private int var;
    private final EnumMap<Good, Integer> inventory;
    private final int[] playerPrices = new int[10];
    private final int[] planetPrices = new int[10];

    /**
     * Market constructor
     * @param player the player looking to buy and sell goods
     * @param planet the planet the player is trading with
     */
    public Market(Player player, SolarSystem planet) {
        this.player = player;
        this.planet = planet;
        Random rand = new Random();
        //var = rand.nextInt(5);
        inventory = new EnumMap<>(Good.class);
        Good[] goods = Good.values();
        for (Good good : goods) {
            inventory.put(good, rand.nextInt(50));
        }
        for (int i = 0; i < 10; i++) {
            playerPrices[i] = calcPrice(getGood(i));
            planetPrices[i] = calcPrice(getGood(i));
            if (playerPrices[i] > planetPrices[i]) {
                playerPrices[i] = planetPrices[i];
            }
        }
    }

    /**
     * Returns the planet's inventory
     * @return planet's inventory
     */
    public EnumMap<Good, Integer> getInventory() {
        return inventory;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Returns the prices of the player's goods on this planet
     * @return array of the prices of the player's goods
     */
    public int[] getPlayerPrices() {
        return playerPrices;
    }

    /**
     * Returns the prices of the planet's goods
     * @return array of the prices of the planet's goods
     */
    public int[] getPlanetPrices() {
        return planetPrices;
    }

    private Good getGood(int good) {
        switch (good) {
            case 0:
                return Good.WATER;
            case 1:
                return Good.FURS;
            case 2:
                return Good.FOOD;
            case 3:
                return Good.ORE;
            case 4:
                return Good.GAMES;
            case 5:
                return Good.FIREARMS;
            case 6:
                return Good.MEDICINE;
            case 7:
                return Good.MACHINES;
            case 8:
                return Good.NARCOTICS;
            case 9:
                return Good.ROBOTS;
        }
        return Good.WATER;
    }

    private int BasePrice(Good good) {
        int price = good.getPrice();
        if (good.equals(Good.WATER)) {
            if (event == Event.DROUGHT) {
                price += 20;
            }
            if (planet.getResource() == Resource.DS) {
                price += 20;
            }
            else if (planet.getResource() == Resource.WA) {
                price -= 20;
            }
        }
        else if (good.equals(Good.FURS)) {
            if (event == Event.COLD) {
                price += 150;
            }
            if (planet.getResource() == Resource.RF) {
                price -= 150;
            }
            else if (planet.getResource() == Resource.LL) {
                price += 150;
            }
        }
        else if (good.equals(Good.FOOD)) {
            if (event == Event.CROPFAIL) {
                price += 50;
            }
            if (planet.getResource() == Resource.RS) {
                price -= 50;
            }
            else if (planet.getResource() == Resource.PS) {
                price += 50;
            }
        }
        else if (good.equals(Good.ORE)) {
            if (event == Event.WAR) {
                price += 200;
            }
            if (planet.getResource() == Resource.MR) {
                price -= 200;
            }
            else if (planet.getResource() == Resource.MP) {
                price += 200;
            }
        }
        else if (good.equals(Good.GAMES)) {
            if (event == Event.BOREDOM) {
                price += 150;
            }
            if (planet.getResource() == Resource.AR) {
                price -= 150;
            }
        }
        else if (good.equals(Good.FIREARMS)) {
            if (event == Event.WAR) {
                price += 500;
            }
            if (planet.getResource() == Resource.WL) {
                price -= 500;
            }
        }
        else if (good.equals(Good.MEDICINE)) {
            if (event == Event.PLAGUE) {
                price += 350;
            }
            if (planet.getResource() == Resource.HE) {
                price -= 350;
            }
        }
        else if (good.equals(Good.MACHINES)) {
            if (event == Event.LACKOFWORKERS) {
                price += 400;
            }
        }
        else if (good.equals(Good.NARCOTICS)) {
            if (event == Event.BOREDOM) {
                price += 1000;
            }
            if (planet.getResource() == Resource.WM) {
                price -= 1000;
            }
        }
        else if (good.equals(Good.ROBOTS)) {
            if (event == Event.LACKOFWORKERS) {
                price += 2000;
            }
        }
        return price;
    }

    private int calcVar(Good good) {
        Random rand = new Random();
        int increase = rand.nextInt(2);
        switch (good) {
            case WATER:
                if (increase == 0) {
                    return -1 * rand.nextInt(5);
                }
                else {
                    return rand.nextInt(5);
                }
            case FURS:
                if (increase == 0) {
                    return -1* rand.nextInt(11);
                }
                else {
                    return rand.nextInt(11);
                }
            case FOOD:
                if (increase == 0) {
                    return -1*rand.nextInt(6);
                }
                else {
                    return rand.nextInt(6);
                }
            case ORE:
                if (increase == 0) {
                    return -1*rand.nextInt(11);
                }
                else {
                    return rand.nextInt(11);
                }
            case GAMES:
                if (increase == 0) {
                    return -1*rand.nextInt(6);
                }
                else {
                    return rand.nextInt(6);
                }
            case FIREARMS:
                if (increase == 0) {
                    return -1*rand.nextInt(11);
                }
                else {
                    return rand.nextInt(11);
                }
            case MEDICINE:
                if (increase == 0) {
                    return -1*rand.nextInt(11);
                }
                else {
                    return rand.nextInt(11);
                }
            case MACHINES:
                if (increase == 0) {
                    return -1*rand.nextInt(6);
                }
                else {
                    return rand.nextInt(6);
                }
            case NARCOTICS:
                if (increase == 0) {
                    return -1*rand.nextInt(16);
                }
                else {
                    return rand.nextInt(16);
                }
            case ROBOTS:
                if (increase == 0) {
                    return -1*rand.nextInt(11);
                }
                else {
                    return rand.nextInt(11);
                }
        }
        return 0;
    }

    private boolean canSell(Good good) {
        switch (good) {
            case WATER:
                return planet.getTechLevel().getLevel() >= 0;
            case FURS:
                return planet.getTechLevel().getLevel() >= 0;
            case FOOD:
                return planet.getTechLevel().getLevel() >= 0;
            case ORE:
                return planet.getTechLevel().getLevel() >= 2;
            case GAMES:
                return planet.getTechLevel().getLevel() >= 1;
            case FIREARMS:
                return planet.getTechLevel().getLevel() >= 1;
            case MEDICINE:
                return planet.getTechLevel().getLevel() >= 1;
            case MACHINES:
                return planet.getTechLevel().getLevel() >= 3;
            case NARCOTICS:
                return planet.getTechLevel().getLevel() >= 0;
            case ROBOTS:
                return planet.getTechLevel().getLevel() >= 4;
        }
        return false;
    }

    private int getMTLP(Good good) {
        switch (good) {
            case WATER:
                return 0;
            case FURS:
                return 0;
            case FOOD:
                return 1;
            case ORE:
                return 2;
            case GAMES:
                return 3;
            case FIREARMS:
                return 3;
            case MEDICINE:
                return 4;
            case MACHINES:
                return 4;
            case NARCOTICS:
                return 5;
            case ROBOTS:
                return 6;
        }
        return 0;
    }

    private int IPL(Good good) {
        switch (good) {
            case WATER:
                return 3;
            case FURS:
                return 10;
            case FOOD:
                return 5;
            case ORE:
                return 20;
            case GAMES:
                return 10;
            case FIREARMS:
                return 75;
            case MEDICINE:
                return 20;
            case MACHINES:
                return 30;
            case NARCOTICS:
                return 125;
            case ROBOTS:
                return 150;
        }
        return 0;
    }

    private int calcPrice(Good good) {
        int base = BasePrice(good);
        return base + (IPL(good)*(planet.getTechLevel().getLevel()-getMTLP(good))) + (base*calcVar(good)/100);
    }

    /**
     * The player buys however many goods they want to from the planet as
     * long as they can afford it, have space in their cargo, and the planet still
     * has the goods in stock
     * @param good the good the player is trying to buy
     * @param quantity how many units of the good the player wants to buy
     * @return string describing the result of the attempted purchase
     */
    public String buy(Good good, int quantity) {
        int price = quantity * planetPrices[good.getNum()];
        if (player.getCredits() < price) {
            return "You do not have enough credits to buy that!";
        }
        if (player.getTotalGoods()+quantity > player.getShip().getCargo()) {
            return "You cannot hold that many goods!";
        }
        if (Objects.requireNonNull(inventory.get(good)) < 1) {
            return "There is/are no " + good.getName() + " left on this planet!";
        }
        player.buy(good, quantity, price);
        inventory.put(good, Objects.requireNonNull(inventory.get(good)) - 1);
        return "Purchase complete";
    }

    /**
     * The player sells goods to the planet in order to make money, as long as
     * the planet's tech level is high enough to buy the good and they do not
     * try to sell more than they have
     * @param good the good the player is trying to sell
     * @param quantity how many units of the good the player wants to sell
     * @return string describing the result of the attempted sale
     */
    public String sell(Good good, int quantity) {
        if (!canSell(good)) {
            return "Planet's tech level is too low to buy this!";
        }
        if (quantity > Objects.requireNonNull(player.getInventory().get(good))) {
            return "You are trying to sell more than you have!";
        }
        player.sell(good, quantity, quantity*playerPrices[good.getNum()]);
        return "Sale complete";
    }
}
