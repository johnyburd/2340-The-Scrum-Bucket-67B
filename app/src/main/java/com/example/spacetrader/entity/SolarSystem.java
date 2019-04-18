package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Random;

/**
 * The solar systems that the player travels between, each of which contain a planet.\
 * @author Scrum Bucket
 * @version 1.0
 */
public class SolarSystem implements Serializable {
    private final String name;
    private final Coordinate location;
    private final TechLevel techLevel;
    private final Resource resource;
    private final Event event;

    /**
     * SolarSystem constructor
     * @param planet the solar system's planet
     * @param techLevel tech level of the solar system
     * @param resource solar system's resource
     * @param event event that may be happening in the solar system
     */
    public SolarSystem(Planet planet, TechLevel techLevel, Resource resource, Event event) {
        this.name = planet.getName();
        this.location = planet.getCoordinates();
        this.techLevel = techLevel;
        this.resource = resource;
        this.event = event;
    }

    /**
     * Checks if the player is attacked by pirates upon arrival
     * @param seed for more random chance
     * @return True if the player is attacked by pirates, false otherwise
     */
    public boolean underAttack(long seed){
        Random rand = new Random(seed);
        int chance = rand.nextInt(100);
        return chance < 10;
    }

    /**
     * Checks if the player is being checked by the police
     * @param seed for more random chance
     * @param player the player
     * @return True if the player is being investigated by the police, false otherwise
     */
    public boolean underArrest(long seed, Player player){
        Random rand = new Random(seed);
        int chance = rand.nextInt(100);
        return chance < player.getPoliceRecord();
    }

    @NonNull
    @Override
    public String toString() {
        return name + "\nlocation: " + location.toString()
                + "\ntech level: " + techLevel.getName()
                + "\nresource: " + resource.getName()
                + "\nevent: " + event.toString();
    }

    /**
     * Returns the solar system's tech level
     * @return solar system's tech level
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Returns the solar system's resource
     * @return solar system's resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Returns the name of the solar system
     * @return solar system's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the solar system
     * @return solar system's location
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * Returns the event occurring in the solar system
     * @return event occurring in the solar system
     */
    public Event getEvent() {
        return event;
    }
}
