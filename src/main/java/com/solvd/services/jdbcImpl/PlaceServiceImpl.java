package com.solvd.services.jdbcImpl;

import com.solvd.bin.Place;
import com.solvd.dao.IPlaceDAO;
import com.solvd.dao.jdbcMySQLImpl.PlaceDAO;
import com.solvd.services.IPlaceServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlaceServiceImpl implements IPlaceServices {
    private Logger LOGGER = LogManager.getLogger(PlaceServiceImpl.class);
    private IPlaceDAO placeDAO = new PlaceDAO();

    @Override
    public void changeLocation(Place place) {
        LOGGER.info("Please insert your new User location: ");
    }

    @Override
    public void changeName(Place place) {

    }

    @Override
    public void placeMenu(Place place) {

    }
}
