package com.solvd.services.jdbcImpl;

import com.solvd.bin.Trip;
import com.solvd.dao.jdbcMySQLImpl.TripDAO;
import com.solvd.services.TripServices;

public class TripServiceImpl implements TripServices {

    @Override
    public void saveTrip(Trip trip) {
        new TripDAO().saveEntity(trip);
    }
    
}
