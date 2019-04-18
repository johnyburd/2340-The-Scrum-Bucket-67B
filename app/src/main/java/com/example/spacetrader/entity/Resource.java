package com.example.spacetrader.entity;

/**
 * Describes planet's resource
 * @author Scrum Bucket
 * @version 1.0
 */
public enum Resource {
    NR (0, "NO SPECIAL RESOURCES"),
    MR (1, "MINERAL RICH"),
    MP (2, "MINERAL POOR"),
    DS (3, "DESERT"),
    WA (4, "LOTS OF WATER"),
    RS (5, "RICH SOIL"),
    PS (6, "POOR SOIL"),
    RF (7, "RICH FAUNA"),
    LL (8, "LIFELESS"),
    WM (9, "WEIRD MUSHROOMS"),
    HE (10, "LOTS OF HERBS"),
    AR (11, "ARTISTIC"),
    WL (12, "WARLIKE");

    private final String name;

    /**
     * Resouce constructor
     * @param name what resource the planet has
     */
    Resource(int code, String name) {
        this.name = name;
    }

    /**
     * Returns the name of the resource the planet has
     * @return name of the resource
     */
    public String getName() {
        return name;
    }
}
