package com.example.spacetrader.models;

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

    TechLevel (int level, String name) {
        this.level = level;
        this.name = name;
    }
}
