package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Random;

/**
 * Ship class, holding the stats off all the different types. Also used for traveling
 * to different planets
 * @author Scrum Bucket
 * @version 1.0
 */
public class Ship implements Serializable {
    private Spaceship ship;
    private int cargo;
    private int currHull;
    private int hull;
    private int currentFuel;
    private int jump;

    /**
     * Default ship constructor
     */
    public Ship() {
        this(Spaceship.GNAT);
    }

    /**
     * Sets the ship's current hull health
     * @param currHull current hull health
     */
    public void setCurrHull(int currHull){
        this.currHull = currHull;
    }

    /**
     * Returns the ship's current hull health
     * @return current hull health
     */
    public int getCurrHull() {
        return currHull;
    }

    /**
     * Returns the ship's max hull health
     * @return max hull health
     */
    public int getHull() {
        return hull;
    }

    /**
     * Ship constructor, set to the given type
     * @param s what type of ship to make
     */
    public Ship(Spaceship s) {
        currentFuel = 300;
        if (s.equals(Spaceship.FLEA)) {
            ship = s;
            cargo = 10;
            hull = 25;
            currHull = 25;
            jump = 20;
        }
        else if (s.equals(Spaceship.GNAT)) {
            ship = s;
            cargo = 15;
            hull = 100;
            currHull = 100;
            jump = 14;
        }
        else if (s.equals(Spaceship.FIREFLY)) {
            ship = s;
            cargo = 20;
            hull = 100;
            currHull = 100;
            jump = 17;
        }
        else if (s.equals(Spaceship.MOSQUITO)) {
            ship = s;
            cargo = 15;
            hull = 100;
            currHull = 100;
            jump = 13;
        }
        else if (s.equals(Spaceship.BUMBLEBEE)) {
            ship = s;
            cargo = 25;
            hull = 100;
            currHull = 100;
            jump = 15;
        }
        else if (s.equals(Spaceship.BEETLE)) {
            ship = s;
            cargo = 50;
            hull = 50;
            currHull = 50;
            jump = 14;
        }
        else if (s.equals(Spaceship.HORNET)) {
            ship = s;
            cargo = 20;
            hull = 150;
            currHull = 150;
            jump = 16;
        }
        else if (s.equals(Spaceship.GRASSHOPPER)) {
            ship = s;
            cargo = 30;
            hull = 150;
            currHull = 150;
            jump = 15;
        }
        else if (s.equals(Spaceship.TERMITE)) {
            ship = s;
            cargo = 60;
            hull = 200;
            currHull = 200;
            jump = 13;
        }
        else if (s.equals(Spaceship.WASP)) {
            ship = s;
            cargo = 35;
            hull = 200;
            currHull = 200;
            jump = 14;
        }
    }

    /**
     * Travels from one planet to another as long as the player has enough fuel
     * @param player the player
     * @param system the solar system the player is trying to travel to
     * @return True if the player has enough fuel to travel, False otherwise
     */
    public boolean Travel(Player player, SolarSystem system) {
        SolarSystem l = player.getLocation();
        int dist = Coordinate.distance(
                l.getLocation(), system.getLocation());
        int consumed = dist / jump;
        if (consumed < currentFuel) {
            currentFuel -= consumed;
            return true;
        }
        return false;
    }

    /**
     * Returns what type of ship this is
     * @return what type of ship this is
     */
    public Spaceship getShip() {return ship;}

    /**
     * Returns how much cargo this ship can hold
     * @return how much cargo this ship can hold
     */
    public int getCargo() {return cargo;}

    /**
     * Returns how much fuel the ship currently has
     * @return how much fuel the ship currently has
     */
    public int getCurrentFuel() {return currentFuel;}

    @NonNull
    public String toString() {return ship.toString();}

    /**
     * Calculates damage done from attacking police.
     * @param seed for more random chance
     * @param fighter fighter points of attacking player
     * @return damage done
     */
    public int attackPolice(long seed, int fighter) {
        Random rand = new Random(seed);
        int damage = rand.nextInt(hull);
        currHull -= Math.max(0, damage - (fighter * 3));
        if (currHull < 0) {
            currHull = 0;
        }
        return damage;
    }

    /**
     * Calculates damage from fleeing from police.
     * @param seed for rand
     * @param fighter fighter points of fleeing player
     * @param pilot pilot points of fleeing player
     * @return damage done
     */
    public int fleePolice(long seed, int fighter, int pilot) {
        Random rand = new Random(seed);
        int luck = pilot * rand.nextInt(10);
        if (luck > 40) {
            return 0; // you got away
        }
        int damage = rand.nextInt(hull);
        currHull -= Math.max(0, (damage / 2) - (fighter * 3));
        //otherwise you are attacked with less damage
        if(currHull < 0) {
            currHull = 0;
        }
        return damage;
    }

    /**
     * Calculates damage from fleeing from pirates.
     * @param seed for rand
     * @param fighter fighter points of fleeing player
     * @param pilot pilot points of fleeing player
     * @return damage done
     */
    public int fleePirates(long seed, int fighter, int pilot) {
        Random rand = new Random(seed);
        int luck = pilot * rand.nextInt(10);
        if (luck > 40) {
            return -1; // you got away
        }
        int damage = rand.nextInt(hull);
        currHull -= Math.max(0, (damage / 2) - (fighter * 3));
        if (currHull < 0) {
            currHull = 0;
        }
        return damage;
    }

    /**
     * Calculates damage done attacking pirates.
     * @param seed for rand
     * @param fighter fighter points of attacking player
     * @return damage done
     */
    public int attackPirates(long seed, int fighter) {
        Random rand = new Random(seed);
        int damage = rand.nextInt(hull);
        currHull -= Math.max(0, damage - (fighter * 3));
        if (currHull < 0) {
            currHull = 0;
        }
        return damage;
    }
}
