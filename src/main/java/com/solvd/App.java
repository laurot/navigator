package com.solvd;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main( String[] args )
    {
        List<Coordinate> path = new Navigator().getPath(new Coordinate(6,0), new Coordinate( 0, -10));
        
        int i = 1;
        for (Coordinate coordinate : path) {
            LOGGER.info(coordinate.toString() + "  -  " + i); 
            i++;
        }
    }
}
