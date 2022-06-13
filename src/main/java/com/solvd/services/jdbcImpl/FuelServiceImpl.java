package com.solvd.services.jdbcImpl;

import com.solvd.bin.Fuel;
import com.solvd.bin.FuelType;
import com.solvd.dao.IFuelDAO;
import com.solvd.dao.jdbcMySQLImpl.FuelDAO;
import com.solvd.services.FuelServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

public class FuelServiceImpl implements FuelServices {
    private final static Logger LOGGER = LogManager.getLogger(FuelServiceImpl.class);
    IFuelDAO fuelDAO = new FuelDAO();
    FuelType fuelType = new FuelType();

    @Override
    public Fuel pickFuelByType(String type) {
        int option;
        try {
            LOGGER.info("Please select your type of fuel: ");
            LOGGER.info("1. Gasoline");
            LOGGER.info("2. Diesel");
            LOGGER.info("3. Ethanol");
            option = Input.getInput().nextInt();
            switch (option) {
                case 1:
                    fuelType.getGasoline();
                    break;
                case 2:
                    fuelType.getDiesel();
                    break;
                case 3:
                    fuelType.getEthanol();
                    break;
                default:
                    LOGGER.info("Not a valid option");
                    break;
            }
            return pickFuelByType(type);
        } catch (InputMismatchException ime) {
            LOGGER.warn("Not a valid input");
            return pickFuelByType(type);
        }
    }
}
