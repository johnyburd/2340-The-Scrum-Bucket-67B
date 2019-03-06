package com.example.spacetrader.entity;

public class Ship {
    private Spaceship ship;
    private int cargo;
    private int currHull;
    private int hull;
    //private int fuel;
    //private int currentFuel;

    public Ship() {
        ship = Spaceship.FLEA;
        cargo = 10;
        hull = 25;
        currHull = 25;
    }

    public Ship(Spaceship s) {
        if (s.equals(Spaceship.FLEA)) {
            cargo = 10;
            hull = 25;
            currHull = 25;
        }
        else if (s.equals(Spaceship.GNAT)) {
            cargo = 15;
            hull = 100;
            currHull = 100;
        }
        else if (s.equals(Spaceship.FIREFLY)) {
            cargo = 20;
            hull = 100;
            currHull = 100;
        }
        else if (s.equals(Spaceship.MOSQUITO)) {
            cargo = 15;
            hull = 100;
            currHull = 100;
        }
        else if (s.equals(Spaceship.BUMBLEBEE)) {
            cargo = 25;
            hull = 100;
            currHull = 100;
        }
        else if (s.equals(Spaceship.BEETLE)) {
            cargo = 50;
            hull = 50;
            currHull = 50;
        }
        else if (s.equals(Spaceship.HORNET)) {
            cargo = 20;
            hull = 150;
            currHull = 150;
        }
        else if (s.equals(Spaceship.GRASSHOPPER)) {
            cargo = 30;
            hull = 150;
            currHull = 150;
        }
        else if (s.equals(Spaceship.TERMITE)) {
            cargo = 60;
            hull = 200;
            currHull = 200;
        }
        else if (s.equals(Spaceship.WASP)) {
            cargo = 35;
            hull = 200;
            currHull = 200;
        }
    }

    public Spaceship getShip() {return ship;}
    public int getCargo() {return cargo;}
    public String toString() {return ship.toString();}
}
