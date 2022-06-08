package com.solvd.dao;

import java.util.List;
import com.solvd.bin.Coordinate;

public interface ICoordinateDAO extends IBaseDAO<Coordinate>{
    List<Coordinate> getAllCoordinates();
}
