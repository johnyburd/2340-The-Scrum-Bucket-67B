package com.example.spacetrader.entity;

public class Player {
    private String name;
    private int skillPoints;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private Spaceship spaceship;

    public Player (){
        skillPoints = 16;
        credits = 1000;
        spaceship = Spaceship.GNAT;
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
        this.spaceship = spaceship;
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

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public String toString() {
        return "Player's name is " + name + " and has " + credits +
                " credits with the " + spaceship.toString() + " ship. " + engineerPoints
                + " points in Engineer, " + fighterPoints + " points in Fighter, " + pilotPoints
                + " points in Pilot, and " + traderPoints + " in Trader.";
    }
}
