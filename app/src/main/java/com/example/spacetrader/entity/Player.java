package com.example.spacetrader.entity;

import java.util.EnumMap;
import java.util.Observable;

public class Player extends Observable {
    private String name;
    private int skillPoints;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private Ship spaceship;
    private SolarSystem location;
    private EnumMap<Good, Integer> inventory;
    private int totalGoods;
    private boolean locationChanged;

    public Player (){
        skillPoints = 16;
        credits = 1000;
        spaceship = new Ship(Spaceship.GNAT);
        inventory = new EnumMap<>(Good.class);
        locationChanged = false;

        Good[] goods = Good.values();
        for (Good good : goods) {
            inventory.put(good, 0);
        }
    }

    public boolean isLocationChanged() {
        if (locationChanged) {
            locationChanged = false;
            return true;
        }
        return false;
    }


    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Player setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
        return this;
    }

    public Player setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
        return this;
    }

    public Player setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
        return this;
    }

    public Player setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
        return this;
    }

    public Player setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
        return this;
    }

    public Player setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public Player setSpaceship(Spaceship spaceship) {
        this.spaceship = new Ship(spaceship);
        return this;
    }

    public Player setLocation(SolarSystem location) {
        this.location = location;
        locationChanged = true;
        this.setChanged();
        this.notifyObservers(credits);
        return this;
    }

    public String getName() {
        return name;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public int getCredits() {
        return credits;
    }

    public int getTotalGoods() {return totalGoods;}

    public Spaceship getSpaceship() {
        return spaceship.getShip();
    }

    public SolarSystem getLocation() {
        return location;
    }

    public EnumMap<Good, Integer> getInventory() {
        return inventory;
    }

    public Ship getShip() {
        return spaceship;
    }

    public String toString() {
        return "Player's name is " + name + " and has " + credits +
                " credits with the " + spaceship.toString() + " ship. " + engineerPoints
                + " points in Engineer, " + fighterPoints + " points in Fighter, " + pilotPoints
                + " points in Pilot, and " + traderPoints + " in Trader.";
    }

    public void buy(Good good, int quantity, int cost) {
        if (inventory.containsKey(good)) {
            inventory.put(good, inventory.get(good)+quantity);
        }
        else {
            inventory.put(good, quantity);
        }
        credits -= cost;
        totalGoods += quantity;
        this.setChanged();
        this.notifyObservers(credits);
    }

    public void sell(Good good, int quantity, int profit) {
        inventory.put(good, inventory.get(good)-quantity);
        credits += profit;
        totalGoods -= quantity;
        this.setChanged();
        this.notifyObservers(credits);
    }
}
