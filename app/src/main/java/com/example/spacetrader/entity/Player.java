package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Observable;
import java.util.Random;

/**
 * The player of the game. Keeps track of their name, stats, ship, location, and inventory
 * @author Scrum Bucket
 * @version 1.0
 */
public class Player extends Observable implements Serializable {
    private String name;
    private final int skillPoints;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private Ship spaceship;
    private SolarSystem location;
    private final EnumMap<Good, Integer> inventory;
    private int totalGoods;
    private boolean locationChanged;
    private boolean marketLocationChanged;

    private int policeRecord;
    private int clout;

    /**
     * Player constructor, starts off with 1000 credits and the GNAT ship
     */
    public Player (){
        skillPoints = 16;
        credits = 1000;
        spaceship = new Ship();
        inventory = new EnumMap<>(Good.class);
        locationChanged = false;

        policeRecord = 50;
        int clout = 50;
        Good[] goods = Good.values();
        for (Good good : goods) {
            inventory.put(good, 0);
        }
    }

    /**
     * Returns if the player has changed location
     * @return True if player has changed location, false otherwise
     */
    public boolean isLocationChanged() {
        if (locationChanged) {
            locationChanged = false;
            return true;
        }
        return false;
    }

    /**
     * Returns if the player has entered a new market
     * @return True if player is in a new market, false otherwise
     */
    public boolean isMarketLocationChanged() {
        if (marketLocationChanged) {
            marketLocationChanged = false;
            return true;
        }
        return false;
    }

    /**
     * Returns how much is needed to bribe the police to get away with illegal goods
     * @param seed For more random chance
     * @return amount of credits needed to bribe the police
     */
    public int bribeAmount(long seed) {
        Random rand = new Random(seed);
        return policeRecord * 10 + rand.nextInt(1000); // after this you either pay or choose another option
    }

    /**
     * Pays the bribe to the police
     * @param amount how much is needed to bribe the police
     */
    public void payBribe(int amount) {
        credits -= amount;
        notifyObservers(credits);
    }

    /**
     * Submits to the police, allowing the player's inventory to be inspected
     * @return True if illegal goods are found, false otherwise
     */
    public boolean submit() {
        boolean found = false;
        if (inventory.get(Good.NARCOTICS) == null || Objects.requireNonNull(inventory.get(Good.NARCOTICS)) > 0) {
            Integer removed = inventory.remove(Good.NARCOTICS);
            if (removed != null) {
                totalGoods -= removed;
            }
            found = true;
        } else if (inventory.get(Good.FIREARMS) == null || Objects.requireNonNull(inventory.get(Good.FIREARMS)) > 0) {
            //Integer removed = inventory.remove(Good.FIREARMS, 0);
            found = true;
        }
        if (found && policeRecord >= 10) {
            policeRecord += 10;
            setCredits(3 * credits / 4); // you lose a fourth of your credits
        } else {
            policeRecord -= 10;
            if (policeRecord < 0) policeRecord = 0;
        }
        notifyObservers(credits);
        return found;
    }

    /**
     * Surrenders to pirates, giving up the items in the player's inventory. If the
     * player has no goods, they surrender half of their credits
     */
    public void surrender() {
        //inventory.clear();
        Good[] goods = Good.values();
        if (totalGoods == 0) {
            setCredits(credits/2);
        }
        else {
            for (Good good : goods) {
                inventory.put(good, 0);
            }
        }
        totalGoods = 0;
        notifyObservers(credits);
    }

    /**
     * Attacks the police and get attacked back
     * @param seed for more random chance
     * @return damage done to the player's ship
     */
    public int attackPolice(long seed) {
        Random rand = new Random(seed);
        int damage = rand.nextInt(spaceship.getHull());
        spaceship.setCurrHull(spaceship.getCurrHull() - Math.max(0, damage - (getFighterPoints() * 3)));
        if(spaceship.getCurrHull() < 0) {
            spaceship.setCurrHull(0);
        }
        setPoliceRecord(getPoliceRecord() + 10);
        //notifyObservers(credits);
        return damage;
    }

    /**
     * Attacks the pirates and get attacked back
     * @param seed for more random chance
     * @return damage done to the player's ship
     */
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
        //notifyObservers(credits);
        return damage;
    }

    /**
     * Attempts to flee from the police. Failing to do so results in taking damage
     * @param seed for more random chance
     * @return damage done to player's ship
     */
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
        //notifyObservers(credits);
        return damage;
    }

    /**
     * Attempts to flee from the pirates. Failing to do so results in taking damage
     * @param seed for more random chance
     * @return damage done to player's ship
     */
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
        //notifyObservers(credits);
        return damage;
    }

    /**
     * Sets the player's name to the given string
     * @param name name of the player
     * @return the player
     */
    public Player setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set's the player's pilot points
     * @param pilotPoints how many pilot points the player will have
     * @return the player
     */
    public Player setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
        return this;
    }

    /**
     * Set's the player's fighter points
     * @param fighterPoints how many fighter points the player will have
     * @return the player
     */
    public Player setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
        return this;
    }

    /**
     * Sets the player's trader points
     * @param traderPoints how many trader points the player will have
     * @return thep player
     */
    public Player setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
        return this;
    }

    /**
     * Sets the player's engineer points
     * @param engineerPoints how many engineer points the player will have
     * @return the player
     */
    public Player setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
        return this;
    }

    /**
     * Sets the player's credits
     * @param credits how many credits the player will have
     * @return the player
     */
    public void setCredits(int credits) {
        this.credits = credits;
        this.setChanged();
        this.notifyObservers(this.credits);
    }

    /**
     * Sets the player's police record
     * @param policeRecord the player's police record
     * @return the player
     */
    public void setPoliceRecord(int policeRecord) {
        this.policeRecord = policeRecord;
    }

    /**
     * Sets the player's clout
     * @param clout the player's clout
     * @return the player
     */
    public void setClout(int clout) {
        this.clout = policeRecord;
    }

    /**
     * Sets the player's spaceship
     * @param spaceship the player's spaceship
     * @return the player
     */
    public Player setSpaceship(Spaceship spaceship) {
        this.spaceship = new Ship(spaceship);
        return this;
    }

    /**
     * Sets the player's location
     * @param location the player's location
     * @return the player
     */
    public void setLocation(SolarSystem location) {
        this.location = location;
        locationChanged = true;
        marketLocationChanged = true;
        this.setChanged();
        this.notifyObservers(credits);
    }

    /**
     * Returns the player's name
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's skill points
     * @return the player's skill points
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * Returns the player's pilot points
     * @return the player's pilot points
     */
    public int getPilotPoints() {
        return pilotPoints;
    }

    /**
     * Returns the player's fighter points
     * @return the player's fighter points
     */
    public int getFighterPoints() {
        return fighterPoints;
    }

    /**
     * Returns the player's trader points
     * @return the player's trader points
     */
    public int getTraderPoints() {
        return traderPoints;
    }

    /**
     * Returns the player's engineer points
     * @return the player's engineer poitns
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     * Returns the player's amount of credits
     * @return the player's credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Returns the player's clout
     * @return the player's clout
     */
    public int getClout() {
        return clout;
    }

    /**
     * Returns the player's police record
     * @return the player's police record
     */
    public int getPoliceRecord() {return policeRecord;}

    /**
     * Returns the player's total amount of goods
     * @return how many goods the player has
     */
    public int getTotalGoods() {return totalGoods;}

    /**
     * Returns the player's spaceship
     * @return the player's spaceship
     */
    public Spaceship getSpaceship() {
        return spaceship.getShip();
    }

    /**
     * Returns which solar system the player is at
     * @return which solar system the player is at
     */
    public SolarSystem getLocation() {
        return location;
    }

    /**
     * Returns the player's inventory
     * @return the player's inventory
     */
    public EnumMap<Good, Integer> getInventory() {
        return inventory;
    }

    /**
     * Returns what type of ship the player has
     * @return player's ship type
     */
    public Ship getShip() {
        return spaceship;
    }

    @NonNull
    public String toString() {
        return "Player's name is " + name + " and has " + credits +
                " credits with the " + spaceship.toString() + " ship. " + engineerPoints
                + " points in Engineer, " + fighterPoints + " points in Fighter, " + pilotPoints
                + " points in Pilot, and " + traderPoints + " in Trader.";
    }

    /**
     * The player buys a certain amount of goods for a price. The goods are added to the inventory
     * and the player pays for them
     * @param good the good the player buys
     * @param quantity how many of the good the player bought
     * @param cost how much the player has to pay for the goods
     */
    public void buy(Good good, int quantity, int cost) {
        if (inventory.containsKey(good)) {
            inventory.put(good, Objects.requireNonNull(inventory.get(good)) + quantity);
        }
        else {
            inventory.put(good, quantity);
        }
        credits -= cost;
        totalGoods += quantity;
        this.setChanged();
        this.notifyObservers(credits);
    }

    /**
     * The player sells a certain amount of goods to the market in order to make money.
     * @param good the good the player is selling
     * @param quantity how many of the good the player is selling
     * @param profit how much money the player makes
     */
    public void sell(Good good, int quantity, int profit) {
        inventory.put(good, Objects.requireNonNull(inventory.get(good)) - quantity);
        credits += profit;
        totalGoods -= quantity;
        this.setChanged();
        this.notifyObservers(credits);
    }
}
