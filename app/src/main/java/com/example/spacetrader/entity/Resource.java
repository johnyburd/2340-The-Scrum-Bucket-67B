package com.example.spacetrader.entity;

/**
 * Describes planet's resource
 * @author Scrum Bucket
 * @version 1.0
 */
public enum Resource {
    NR ("NO SPECIAL RESOURCES"),
    MR ("MINERAL RICH"),
    MP ("MINERAL POOR"),
    DS ("DESERT"),
    WA ("LOTS OF WATER"),
    RS ("RICH SOIL"),
    PS ("POOR SOIL"),
    RF ("RICH FAUNA"),
    LL ("LIFELESS"),
    WM ("WEIRD MUSHROOMS"),
    HE ("LOTS OF HERBS"),
    AR ("ARTISTIC"),
    WL ("WARLIKE");

    private final String name;

    /**
     * Resouce constructor
     * @param name what resource the planet has
     */
    Resource(String name) {
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
