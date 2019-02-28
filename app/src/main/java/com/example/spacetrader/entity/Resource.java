package com.example.spacetrader.entity;

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

    private int code;
    private String name;

    Resource(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
