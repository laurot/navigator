package com.solvd.bin;

import java.util.List;
import java.util.Objects;

public class Path {
    private int id;
    private List<Coordinate> path;
    private double distance;

    public Path(List<Coordinate> path, double distance) {
        this.path = path;
        this.distance = distance;
    }

    public Path() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        return id == path1.id && Double.compare(path1.distance, distance) == 0 && Objects.equals(path, path1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, distance);
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path=" + path +
                ", distance=" + distance +
                '}';
    }
}
