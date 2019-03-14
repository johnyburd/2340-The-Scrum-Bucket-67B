package com.example.spacetrader.entity;

public enum Good {
    WATER (30, "WATER"),
    FURS (250, "FURS"),
    FOOD (100, "FOOD"),
    ORE (350, "ORE"),
    GAMES (250, "GAMES"),
    FIREARMS (1250, "FIREARMS"),
    MEDICINE (650, "MEDICINE"),
    MACHINES (650, "MACHINES"),
    NARCOTICS (3500, "NARCOTICS"),
    ROBOTS (5000, "ROBOTS");

    private int price;
    private String name;

    Good (int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {return price;}
    public String getName() {return name;}
}
