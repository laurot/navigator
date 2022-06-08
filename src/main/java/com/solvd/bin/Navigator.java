package com.solvd.bin;

import com.solvd.bin.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Navigator {
    
    public List<Coordinate> getPath(Coordinate start, Coordinate destination){

        Set<Coordinate> allDots = getAllDots();
        allDots.add(start);
        allDots.add(destination);
        List<Coordinate> path = new ArrayList<Coordinate>();
        Coordinate nextDot = start;
        Set<Coordinate> removables = new HashSet<Coordinate>();
        while(!destination.equals(nextDot)){
            path.add(nextDot);
            allDots.remove(nextDot);
            for (Coordinate coordinate : allDots) {
                
                if(nextDot.equals(path.get(path.size()-1))) nextDot = coordinate;

                if(!path.get(path.size()-1).equals(coordinate)){
                    double distanceNext = getDistance(coordinate, destination);
                    double distance = getDistance(path.get(path.size()-1), destination);
                    if (distanceNext < distance) {
                        double dNext = getDistance(coordinate, path.get(path.size()-1));
                        double dCurrent = getDistance(nextDot, path.get(path.size()-1));
                        nextDot = dNext > dCurrent ? nextDot:coordinate;
                    }else{
                        removables.add(coordinate); //Makes a list of coordinates to be 
                    }                               //later removed and make future iterations faster
                }
            }
            allDots.removeAll(removables);          //Removes them 
        }
        path.add(destination);
        return path;
    }

    private Set<Coordinate> getAllDots(){
        Set<Coordinate> allDots = new HashSet<Coordinate>();
        for (int i = 0; i < 15; i++) {
            allDots.add(new Coordinate(ThreadLocalRandom.current().nextInt(30)-15,ThreadLocalRandom.current().nextInt(30)-15));
        }
        return allDots;
    }

    private double getDistance(Coordinate a, Coordinate b){
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        return Math.sqrt(x*x+y*y);
    }
}
