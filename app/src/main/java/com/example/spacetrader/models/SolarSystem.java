package com.example.spacetrader.models;

public class SolarSystem {
    private String name;
    private Coordinate location;
    private TechLevel techLevel;
    private Resource resource;

    public SolarSystem(String name, Coordinate location, TechLevel techLevel, Resource resource) {
        this.name = name;
        this.location = location;
        this.techLevel = techLevel;
        this.resource = resource;
    }

}
