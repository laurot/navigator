package com.solvd;

public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate() {
    }
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate coord = (Coordinate) obj;
        if (coord.hashCode() != this.hashCode()) return false;
        if (coord.getX() != this.x) return false;
        if (coord.getY() != this.y) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return x+y;
    }

    public String nameToString(){
        return "("+x+","+y+")";
    }
}
