package com.example.spacetrader.entity;

public class Market {

    public Market() {

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
}
