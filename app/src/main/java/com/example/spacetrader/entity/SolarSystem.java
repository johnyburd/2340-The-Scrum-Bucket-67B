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

    public SolarSystem(Planet planet, TechLevel techLevel, Resource resource) {
        this.name = planet.getName();
        this.location = planet.getCoordinates();
        this.techLevel = techLevel;
        this.resource = resource;
    }

    @Override
    public String toString() {
        return name + " | location: " + location.toString()
                + ", tech level: " + techLevel.getName()
                + ", resource: " + resource.getName();
    }

}
