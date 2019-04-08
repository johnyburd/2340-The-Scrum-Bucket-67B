package com.example.spacetrader.entity;

import android.media.Image;
import java.util.Random;

import java.io.Serializable;
import java.util.Random;

public class Ship implements Serializable {
    private Spaceship ship;
    private int cargo;
    private int currHull;
    private int hull;
    private int fuel;
    private int currentFuel;
    private int jump;

    public Ship() {
        ship = Spaceship.FLEA;
        cargo = 10;
        hull = 25;
        currHull = 25;
        currentFuel = 300;
        jump = 20;
    }
    public void setCurrHull(int currHull){
        this.currHull = currHull;
    }
    public void setHull(int hull){
        this.hull = hull;
    }

    public int getCurrHull() {
        return currHull;
    }

    public int getHull() {
        return hull;
    }
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

    public boolean Travel(Player player, SolarSystem system) {
        Coordinate start = player.getLocation().getLocation();
        Coordinate dest = system.getLocation();
        double dist = Math.sqrt(Math.pow(start.getX() - dest.getX(), 2) + Math.pow(start.getY() - dest.getY(), 2));
        int consumed = (int)dist/jump;
        if (consumed < currentFuel) {
            currentFuel -= consumed;
            return true;
        }
        return false;
    }

    public Spaceship getShip() {return ship;}
    public int getCargo() {return cargo;}
    public int getCurrentFuel() {return currentFuel;}
    public String toString() {return ship.toString();}
}
