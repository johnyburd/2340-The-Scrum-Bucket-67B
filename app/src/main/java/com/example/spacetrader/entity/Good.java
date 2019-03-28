package com.example.spacetrader.entity;

public enum Good {
    WATER (0, 30, "WATER"),
    FURS (1, 250, "FURS"),
    FOOD (2, 100, "FOOD"),
    ORE (3, 350, "ORE"),
    GAMES (4, 250, "GAMES"),
    FIREARMS (5, 1250, "FIREARMS"),
    MEDICINE (6, 650, "MEDICINE"),
    MACHINES (7, 650, "MACHINES"),
    NARCOTICS (8, 3500, "NARCOTICS"),
    ROBOTS (9, 5000, "ROBOTS");

    private int num;
    private int price;
    private String name;

    Good (int num, int price, String name) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    public int getNum() {return num;}
    public int getPrice() {return price;}
    public String getName() {return name;}
}
