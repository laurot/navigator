package com.solvd.services;

import java.util.List;
import java.util.Set;

import com.solvd.bin.Coordinate;

public interface CoordinateServices {
    Set<Coordinate> getAllDots();                  //trae todos los datos de coordinate table
    void saveCoordinate(Coordinate coordinate);     //inserta nueva coordenada -Que no se repita con una ya existente-
}
