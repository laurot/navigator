package com.solvd.bin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Path;
import com.solvd.bin.Trip;

public class Navigator {
    
    public Path getpathList(Trip trip, Coordinate destination){

        Path path = new Path();
        Set<Coordinate> allDots = getAllDots();                         //Has to be changed
        Coordinate nextDot = trip.getUser().getPosition();
        allDots.add(nextDot);
        allDots.add(destination);
        List<Coordinate> pathList = new ArrayList<Coordinate>();
        Set<Coordinate> removables = new HashSet<Coordinate>();
        while(!destination.equals(nextDot)){
            pathList.add(nextDot);
            allDots.remove(nextDot);
            for (Coordinate coordinate : allDots) {
                
                if(nextDot.equals(pathList.get(pathList.size()-1))) nextDot = coordinate;

                if(!pathList.get(pathList.size()-1).equals(coordinate)){
                    double distanceNext = getDistance(coordinate, destination);
                    double distance = getDistance(pathList.get(pathList.size()-1), destination);
                    if (distanceNext < distance) {
                        double dNext = getDistance(coordinate, pathList.get(pathList.size()-1));
                        double dCurrent = getDistance(nextDot, pathList.get(pathList.size()-1));
                        if(dNext < dCurrent){
                            nextDot = coordinate;
                            path.setDistance(path.getDistance()+dNext);
                        }
                    }else{
                        removables.add(coordinate); //Makes a list of coordinates to be 
                    }                               //later removed and make future iterations faster
                }
            }
            allDots.removeAll(removables);          //Removes them 
        }
        pathList.add(destination);
        path.setPath(pathList);

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
