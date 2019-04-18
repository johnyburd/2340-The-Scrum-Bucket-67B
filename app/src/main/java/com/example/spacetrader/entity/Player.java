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
    private final String name;
    private final int pilotPoints;
    private final int fighterPoints;
    private final int traderPoints;
    private final int engineerPoints;
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
        this("Player", 4, 4, 4, 4);
    }

    /**
     * Creates a new player with the given parameters.
     * @param name player name
     * @param pilot pilot skill points
     * @param fighter fighter skill points
     * @param trader trader skill points
     * @param engineer engineer skill points
     */
    public Player(String name, int pilot, int fighter, int trader, int engineer) {
        credits = 1000;
        spaceship = new Ship();
        locationChanged = false;
        policeRecord = 50;
        clout = 50;
        inventory = new EnumMap<>(Good.class);
        Good[] goods = Good.values();
        for (Good good : goods) {
            inventory.put(good, 0);
        }
        this.name = name;
        pilotPoints = pilot;
        fighterPoints = fighter;
        traderPoints = trader;
        engineerPoints = engineer;
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
        return (policeRecord * 10) + rand.nextInt(1000);
        // after this you either pay or choose another option
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
        if ((inventory.get(Good.NARCOTICS) == null)
                || (Objects.requireNonNull(inventory.get(Good.NARCOTICS)) > 0)) {
            Integer removed = inventory.remove(Good.NARCOTICS);
            if (removed != null) {
                totalGoods -= removed;
            }
            found = true;
        } else if ((inventory.get(Good.FIREARMS) == null)
                || (Objects.requireNonNull(inventory.get(Good.FIREARMS)) > 0)) {
            //Integer removed = inventory.remove(Good.FIREARMS, 0);
            found = true;
        }
        if (found && (policeRecord >= 10)) {
            policeRecord += 10;
            setCredits((3 * credits) / 4); // you lose a fourth of your credits
        } else {
            policeRecord -= 10;
            if (policeRecord < 0) {
                policeRecord = 0;
            }
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
        setPoliceRecord(getPoliceRecord() + 10);
        return spaceship.attackPolice(seed, fighterPoints);
    }

    /**
     * Attacks the pirates and get attacked back
     * @param seed for more random chance
     * @return damage done to the player's ship
     */
    public int attackPirates(long seed) {
        int damage = spaceship.attackPirates(seed, fighterPoints);
        if (damage < 10) {
            clout -= 5;
        } else {
            clout += 5;
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
        return spaceship.fleePolice(seed, fighterPoints, pilotPoints);
    }

    /**
     * Attempts to flee from the pirates. Failing to do so results in taking damage
     * @param seed for more random chance
     * @return damage done to player's ship
     */
    public int fleePirates(long seed) {
        int damage = spaceship.fleePirates(seed, fighterPoints, pilotPoints);
        if (damage == -1) {
            clout += 5;
            damage = 0;
        } else if (damage < 10) {
            clout -= 5;
        } else {
            clout += 5;
        }
        return damage;
    }

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Sets the player's name to the given string
//     * @param name name of the player
//     * @return the player
//     */
//    public Player setName(String name) {
//        this.name = name;
//        return this;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Set's the player's pilot points
//     * @param pilotPoints how many pilot points the player will have
//     * @return the player
//     */
//    public Player setPilotPoints(int pilotPoints) {
//        this.pilotPoints = pilotPoints;
//        return this;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Set's the player's fighter points
//     * @param fighterPoints how many fighter points the player will have
//     * @return the player
//     */
//    public Player setFighterPoints(int fighterPoints) {
//        this.fighterPoints = fighterPoints;
//        return this;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:56 PM):
//    /**
//     * Sets the player's trader points
//     * @param traderPoints how many trader points the player will have
//     * @return thep player
//     */
//    public Player setTraderPoints(int traderPoints) {
//        this.traderPoints = traderPoints;
//        return this;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:56 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Sets the player's engineer points
//     * @param engineerPoints how many engineer points the player will have
//     * @return the player
//     */
//    public Player setEngineerPoints(int engineerPoints) {
//        this.engineerPoints = engineerPoints;
//        return this;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

    /**
     * Sets the player's credits
     * @param credits how many credits the player will have
     */
    public void setCredits(int credits) {
        this.credits = credits;
        this.setChanged();
        this.notifyObservers(this.credits);
    }

    /**
     * Sets the player's police record
     * @param policeRecord the player's police record
     */
    private void setPoliceRecord(int policeRecord) {
        this.policeRecord = policeRecord;
    }

    /**
     * Sets the player's spaceship
     * @param spaceship the player's spaceship
     */
    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = new Ship(spaceship);
    }

    /**
     * Sets the player's location
     * @param location the player's location
     */
    public void setLocation(SolarSystem location) {
        this.location = location;
        locationChanged = true;
        marketLocationChanged = true;
        this.setChanged();
        this.notifyObservers(credits);
    }

    /**
     * @param num Number to set totalGoods to
     */
    public void setTotalGoods(int num) {
        totalGoods = num;
    }

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Returns the player's name
//     * @return the player's name
//     */
//    public String getName() {
//        return name;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Returns the player's pilot points
//     * @return the player's pilot points
//     */
//    public int getPilotPoints() {
//        return pilotPoints;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Returns the player's fighter points
//     * @return the player's fighter points
//     */
//    public int getFighterPoints() {
//        return fighterPoints;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:57 PM):
//    /**
//     * Returns the player's trader points
//     * @return the player's trader points
//     */
//    public int getTraderPoints() {
//        return traderPoints;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:57 PM)

// --Commented out by Inspection START (4/18/2019 3:56 PM):
//    /**
//     * Returns the player's engineer points
//     * @return the player's engineer poitns
//     */
//    public int getEngineerPoints() {
//        return engineerPoints;
//    }
// --Commented out by Inspection STOP (4/18/2019 3:56 PM)

    /**
     * Returns the player's amount of credits
     * @return the player's credits
     */
    public int getCredits() {
        return credits;
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
     * Returns which solar system the player is at
     * @return which solar system the player is at
     */
    public SolarSystem getLocation() {
        return location;
    }

    /**
     * @return location of player as a String
     */
    public String getLocationName() {
        return location.getName();
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

    /**
     * @param seed to generate Random object
     * @return whether player is under attack
     */
    public boolean underAttack(long seed) {
        return location.underAttack(seed);
    }

    public boolean underArrest(long seed) {
        return location.underArrest(seed, this);
    }

    public void travel(SolarSystem system) {
        if (spaceship.Travel(this, system)) {
            setLocation(system);
        }
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
     * @return success/failure message
     */
    public String buy(Good good, int quantity, int cost) {
        if (credits < cost) {
            return "You do not have enough credits to buy that!";
        }
        if ((totalGoods + quantity) > spaceship.getCargo()) {
            return "You cannot hold that many goods!";
        }
        if (inventory.containsKey(good)) {
            inventory.put(good, Objects.requireNonNull(inventory.get(good)) + quantity);
        } else {
            inventory.put(good, quantity);
        }
        credits -= cost;
        totalGoods += quantity;
        this.setChanged();
        this.notifyObservers(credits);
        return "Purchase complete";
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
