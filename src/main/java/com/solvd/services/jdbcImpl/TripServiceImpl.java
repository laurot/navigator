package com.solvd.services.jdbcImpl;

import com.solvd.bin.Trip;
import com.solvd.dao.ITripDAO;
import com.solvd.dao.jdbcMySQLImpl.TripDAO;
import com.solvd.services.ITripServices;

public class TripServiceImpl implements ITripServices{

    @Override
    public void saveTrip(Trip trip) {
        ITripDAO tripDAO = new TripDAO();
        tripDAO.saveEntity(trip);
    }
    
}
