package com.example.spacetrader.entity;

import java.util.EnumMap;
import java.util.Random;

public class Market {

    private Player player;
    private SolarSystem planet;
    private Event event;
    private int var;

    public Market(Player player, SolarSystem planet, Event event) {
        this.player = player;
        this.planet = planet;
        this.event = event;
        Random rand = new Random();
        var = rand.nextInt(5);
    }

    private int BasePrice(Good good, Event event) {
        if (good.equals(Good.WATER)) {
            if (event == Event.DROUGHT) {
                return 50;
            }
            return 30;
        }
        else if (good.equals(Good.FURS)) {
            if (event == Event.COLD) {
                return 400;
            }
            return 250;
        }
        else if (good.equals(Good.FOOD)) {
            if (event == Event.CROPFAIL) {
                return 150;
            }
            return 100;
        }
        else if (good.equals(Good.ORE)) {
            if (event == Event.WAR) {
                return 550;
            }
            return 350;
        }
        else if (good.equals(Good.GAMES)) {
            if (event == Event.BOREDOM) {
                return 400;
            }
            return 250;
        }
        else if (good.equals(Good.FIREARMS)) {
            if (event == Event.WAR) {
                return 1750;
            }
            return 1250;
        }
        else if (good.equals(Good.MEDICINE)) {
            if (event == Event.PLAGUE) {
                return 1000;
            }
            return 650;
        }
        else if (good.equals(Good.MACHINES)) {
            if (event == Event.LACKOFWORKERS) {
                return 1300;
            }
            return 900;
        }
        else if (good.equals(Good.NARCOTICS)) {
            if (event == Event.BOREDOM) {
                return 4500;
            }
            return 3500;
        }
        else if (good.equals(Good.ROBOTS)) {
            if (event == Event.LACKOFWORKERS) {
                return 7000;
            }
            return 5000;
        }
        else return -1;
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
                return -10;
            case FIREARMS:
                return -75;
            case MEDICINE:
                return -20;
            case MACHINES:
                return -30;
            case NARCOTICS:
                return -125;
            case ROBOTS:
                return -150;
        }
        return 0;
    }

    private int calcPrice(Good good, Event event) {
        return BasePrice(good, event) + (IPL(good)*(planet.getTechLevel().getLevel()-getMTLP(good))) + BasePrice(good, event)*calcVar(good);
    }
}
