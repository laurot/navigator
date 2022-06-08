package com.solvd.bin;

import java.util.List;

public class Path {
    private List<Coordinate> path;
    private double distance;
    
    public List<Coordinate> getPath() {
        return path;
    }
    public void setPath(List<Coordinate> path) {
        this.path = path;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
