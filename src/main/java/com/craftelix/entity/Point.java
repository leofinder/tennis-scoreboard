package com.craftelix.entity;

public enum Point {
    POINTS_0("0"),
    POINTS_15("15"),
    POINTS_30("30"),
    POINTS_40("40"),
    POINTS_AD("AD");

    public final String label;

    Point(String label) {
        this.label = label;
    }
}
