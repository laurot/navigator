package com.solvd.services;

import com.solvd.bin.Place;

public interface IPlaceServices {
    void changeLocation(Place place);   //cambia coordenada del lugar (si ya existe solo cambia el location_id, sino la crea primero)
    void changeName(Place place);       //cambia el nombre del lugar
    void placeMenu(Place place);        //Opciones para que hacer en place (cambiar: location, nombre, user/pass, borrar)
}
