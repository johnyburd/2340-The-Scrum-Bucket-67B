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

    private int BasePrice(Good good) {
        if (good.equals(Good.WATER)) {
            return 30;
        }
        else if (good.equals(Good.FURS)) {
            return 250;
        }
        else if (good.equals(Good.FOOD)) {
            return 100;
        }
        else if (good.equals(Good.ORE)) {
            return 350;
        }
        else if (good.equals(Good.GAMES)) {
            return 250;
        }
        else if (good.equals(Good.FIREARMS)) {
            return 1250;
        }
        else if (good.equals(Good.MEDICINE)) {
            return 650;
        }
        else if (good.equals(Good.MACHINES)) {
            return 900;
        }
        else if (good.equals(Good.NARCOTICS)) {
            return 3500;
        }
        else if (good.equals(Good.ROBOTS)) {
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

    private int calcPrice(Good good, Event condition) {
        //TODO
        return 0;
    }
}
