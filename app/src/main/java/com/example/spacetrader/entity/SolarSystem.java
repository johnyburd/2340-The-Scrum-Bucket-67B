package com.example.spacetrader.entity;

import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.TechLevel;

import java.io.Serializable;
import java.util.Random;

public class SolarSystem implements Serializable {
    private String name;
    private Coordinate location;
    private TechLevel techLevel;
    private Resource resource;
    private Event event;

    public SolarSystem(Planet planet, TechLevel techLevel, Resource resource, Event event) {
        this.name = planet.getName();
        this.location = planet.getCoordinates();
        this.techLevel = techLevel;
        this.resource = resource;
        this.event = event;
    }

    public boolean underAttack(long seed, Player player){
        Random rand = new Random(seed);
        int chance = rand.nextInt(100);
        return chance < 10;
    }

    public boolean underArrest(long seed, Player player){
        Random rand = new Random(seed);
        int chance = rand.nextInt(100);
        return chance < player.getPoliceRecord();
    }

    @Override
    public String toString() {
        return name + "\nlocation: " + location.toString()
                + "\ntech level: " + techLevel.getName()
                + "\nresource: " + resource.getName()
                + "\nevent: " + event.toString();
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Resource getResource() {
        return resource;
    }

    public String getName() {
        return name;
    }

    public Coordinate getLocation() {
        return location;
    }

    public Event getEvent() {
        return event;
    }
}
