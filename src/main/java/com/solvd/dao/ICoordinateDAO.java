package com.solvd.dao;

import java.util.List;
import java.util.Set;

import com.solvd.bin.Coordinate;

public interface ICoordinateDAO extends IBaseDAO<Coordinate>{
    Set<Coordinate> getAllCoordinates();
}
