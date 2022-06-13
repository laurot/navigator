package com.solvd.services.jdbcImpl;

import com.solvd.bin.Coordinate;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.dao.jdbcMySQLImpl.CoordinateDAO;
import com.solvd.services.CoordinateServices;
import java.util.Set;

public class CoordinateServiceImpl implements CoordinateServices {
    private ICoordinateDAO coordinateDAO = new CoordinateDAO();

    @Override
    public Set<Coordinate> getAllDots() {
        return coordinateDAO.getAllCoordinates();
    }

    @Override
    public void saveCoordinate(Coordinate coordinate) {
        coordinateDAO.saveEntity(coordinate);
    }
}
