package com.solvd.services;

import java.util.List;

import com.solvd.bin.Coordinate;

public interface ICoordinateServices {
    List<Coordinate> getAllDots();                  //trae todos los datos de coordinate table
    void saveCoordinate(Coordinate coordinate);     //inserta nueva coordenada -Que no se repita con una ya existente-
}
