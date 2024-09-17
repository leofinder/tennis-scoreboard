package com.craftelix.entity;

public enum Point {
    POINTS_0,
    POINTS_15,
    POINTS_30,
    POINTS_40,
    POINTS_AD;

    public Point next() {
        Point[] points = values();
        return points[(this.ordinal() + 1) % points.length];
    }
}
