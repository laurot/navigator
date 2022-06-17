package com.solvd.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.solvd.bin.Coordinate;
import com.solvd.bin.Path;
import com.solvd.bin.Trip;
import com.solvd.services.jdbcImpl.CoordinateServiceImpl;

public class Navigator {
    
    public void Navigate(Trip trip, Coordinate destination){

        Path path = new Path();
        Set<Coordinate> allDots = new CoordinateServiceImpl().getAllDots();                         //Has to be changed
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
        path.setDistance(path.getDistance() + getDistance(pathList.get(pathList.size()-1), destination));
        pathList.add(destination);
        path.setPath(pathList);
        trip.setPath(path);
    }

    private double getDistance(Coordinate a, Coordinate b){
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        return Math.sqrt(x*x+y*y);
    }
}
