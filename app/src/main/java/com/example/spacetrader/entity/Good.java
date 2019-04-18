package com.example.spacetrader.entity;


/**
 * The goods that can be traded for
 * @author Scrum Bucket
 * @version 1.0
 */
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

    private final int num;
    private final int price;
    private final String name;

    /**
     * Good constructor
     * @param num the good's ID
     * @param price the price of the good
     * @param name the name of the good
     */
    Good (int num, int price, String name) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    /**
     * Returns the ID
     * @return ID of the good
     */
    public int getNum() {return num;}

    /**
     * Returns the price
     * @return price of the good
     */
    public int getPrice() {return price;}

    /**
     * Returns the name of the good
     * @return name of the good
     */
    public String getName() {return name;}
}
