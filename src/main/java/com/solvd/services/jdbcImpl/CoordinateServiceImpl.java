package com.solvd.services.jdbcImpl;

import com.solvd.bin.Coordinate;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.dao.jdbcMySQLImpl.CoordinateDAO;
import com.solvd.services.ICoordinateServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CoordinateServiceImpl implements ICoordinateServices {
    private Logger LOGGER = LogManager.getLogger(CoordinateServiceImpl.class);
    private ICoordinateDAO coordinateDAO = new CoordinateDAO();

    @Override
    public List<Coordinate> getAllDots() {
        return coordinateDAO.getAllCoordinates();

    }

    @Override
    public void saveCoordinate(Coordinate coordinate) {
        coordinateDAO.saveEntity(coordinate);
    }
}
