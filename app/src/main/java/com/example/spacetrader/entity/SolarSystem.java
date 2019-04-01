package com.example.spacetrader.entity;

import com.example.spacetrader.entity.Coordinate;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Resource;
import com.example.spacetrader.entity.TechLevel;

public class SolarSystem {
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
