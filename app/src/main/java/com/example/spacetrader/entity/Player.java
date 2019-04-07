package com.example.spacetrader.entity;

import java.util.EnumMap;
import java.util.Observable;
import java.util.Random;

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
    private int policeRecord;
    private int clout;
    public Player (){
        skillPoints = 16;
        credits = 1000;
        spaceship = new Ship(Spaceship.GNAT);
        inventory = new EnumMap<>(Good.class);
        policeRecord = 50;
        int clout = 50;
        Good[] goods = Good.values();
        for (Good good : goods) {
            inventory.put(good, 0);
        }
    }

    public int bribeAmount(long seed) {
        Random rand = new Random(seed);
        int amount = policeRecord * 10 + rand.nextInt(1000);
        return amount; // after this you either pay or choose another option
    }

    public void payBribe(int amount) {
        credits -= amount;
    }

    public boolean submit() {
        boolean found = false;
        if (inventory.get(Good.NARCOTICS) == null || inventory.get(Good.NARCOTICS) > 0) {
            inventory.remove(Good.NARCOTICS);
            found = true;
        } else if (inventory.get(Good.FIREARMS) == null || inventory.get(Good.FIREARMS) > 0) {
            inventory.remove(Good.FIREARMS, 0);
            found = true;
        }
        if (found && policeRecord >= 10) {
            policeRecord -= 10;
            credits =  3 * credits / 4; // you lose a fourth of your credits
        } else {
            policeRecord += 10;
        }
        return found;
    }

    public void surrender() {
        inventory.clear();
    }

    public int attackPolice(long seed) {
        Random rand = new Random(seed);
        int damage = rand.nextInt(spaceship.getHull());
        spaceship.setCurrHull(spaceship.getCurrHull() - Math.max(0, damage - (getFighterPoints() * 3)));
        if(spaceship.getCurrHull() < 0) {
            spaceship.setCurrHull(0);
        }
        setPoliceRecord(getPoliceRecord() + 10);
        return damage;
    }

    public int attackPirates(long seed) {
        Random rand = new Random(seed);
        int damage = rand.nextInt(spaceship.getHull());
        spaceship.setCurrHull(spaceship.getCurrHull() - Math.max(0, damage - (getFighterPoints() * 3)));
        if(spaceship.getCurrHull() < 0) {
            spaceship.setCurrHull(0);
        }
        if (damage < 10) {
            setClout(getClout() - 5);
        } else {
            setClout(getClout() + 5);
        }
        return damage;
    }

    public int fleePolice(long seed) {
        setPoliceRecord(getPoliceRecord() + 10);
        Random rand = new Random(seed);
        int luck = getPilotPoints() * rand.nextInt(10);
        if (luck > 40) {
            return 0; // you got away
        }
        int damage = rand.nextInt(spaceship.getHull());
        spaceship.setCurrHull(spaceship.getCurrHull() - Math.max(0, (damage / 2) - (getFighterPoints() * 3))); //otherwise you are attacked with less damage
        if(spaceship.getCurrHull() < 0) {
            spaceship.setCurrHull(0);
        }
        return damage;
    }

    public int fleePirates(long seed) {
        Random rand = new Random(seed);
        int luck = getPilotPoints() * rand.nextInt(10);
        if (luck > 40) {
            setClout(getClout() + 5);
            return 0; // you got away
        }
        int damage = rand.nextInt(spaceship.getHull());
        spaceship.setCurrHull(spaceship.getCurrHull() - Math.max(0, (damage / 2) - (getFighterPoints() * 3))); //otherwise you are attacked with less damage
        if(spaceship.getCurrHull() < 0) {
            spaceship.setCurrHull(0);
        }
        if (damage < 10) {
            setClout(getClout() - 5);
        } else {
            setClout(getClout() + 5);
        }
        return damage;
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

    public Player setPoliceRecord(int policeRecord) {
        this.policeRecord = policeRecord;
        return this;
    }

    public Player setClout(int clout) {
        this.clout = policeRecord;
        return this;
    }

    public Player setSpaceship(Spaceship spaceship) {
        this.spaceship = new Ship(spaceship);
        return this;
    }

    public Player setLocation(SolarSystem location) {
        this.location = location;
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

    public int getClout() {
        return clout;
    }

    public int getPoliceRecord() {return policeRecord;}

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
