package com.solvd.services.jdbcImpl;

import com.solvd.bin.Fuel;
import com.solvd.dao.IFuelDAO;
import com.solvd.dao.jdbcMySQLImpl.FuelDAO;
import com.solvd.services.FuelServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FuelServiceImpl implements FuelServices {
    private final static Logger LOGGER = LogManager.getLogger(FuelServiceImpl.class);
    IFuelDAO fuelDAO = new FuelDAO();
    Fuel fuel = new Fuel();

    @Override
    public Fuel pickFuelByType(String type) {
        LOGGER.info("Please select your type of fuel: ");
        fuel.setType(Input.getInput().nextLine());
        return fuelDAO.getEntityByType(type);
    }
}
