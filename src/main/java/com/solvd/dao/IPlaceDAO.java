package com.solvd.dao;

import java.util.Set;

import com.solvd.bin.Place;

public interface IPlaceDAO extends IBaseDAO<Place>{
    Set<Place> getAllPlaces();
}
