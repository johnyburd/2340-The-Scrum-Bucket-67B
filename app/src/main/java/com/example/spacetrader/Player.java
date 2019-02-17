package com.example.spacetrader;

public class Player {
    String name;
    int skillPoints;
    int pilotPoints;
    int fighterPoints;
    int traderPoints;
    int engineerPoints;
    int credits;
    Spaceship spaceship;

    public Player (){
        skillPoints = 16;
        credits = 1000;
        spaceship = Spaceship.GNAT;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
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
}
