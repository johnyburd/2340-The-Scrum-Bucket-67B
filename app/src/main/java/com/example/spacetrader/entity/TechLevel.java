package com.example.spacetrader.entity;

/**
 * Techlevel enum, describing the different tech levels
 * @author Scrum Bucket
 * @version 1.0
 */
public enum TechLevel {
    PA (0, "PRE-AGRICULTURE"),
    AG (1, "AGRICULTURE"),
    MD (2, "MEDIEVAL"),
    RN (3, "RENAISSANCE"),
    EI (4, "EARLY INDUSTRIAL"),
    IN (5, "INDUSTRIAL"),
    PI (6, "POST INDUSTRIAL"),
    HT (7, "HI-TECH");

    private int level;
    private String name;

    /**
     * TechLevel constructor
     * @param level the tech level
     * @param name name of the tech level
     */
    TechLevel (int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * Returns the tech level
     * @return tech level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the name of the tech level
     * @return name of the tech level
     */
    public String getName() {
        return name;
    }
}
