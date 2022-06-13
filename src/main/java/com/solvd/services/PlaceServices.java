package com.solvd.services;

import com.solvd.bin.Place;

public interface PlaceServices {
    Place findPlaceByName(String name);
    void changeLocation(Place place);   //cambia coordenada del lugar (si ya existe solo cambia el location_id, sino la crea primero)
    void changeName(Place place);       //cambia el nombre del lugar
    void placeMenu();        //Opciones para que hacer en place (cambiar: location, nombre, user/pass, borrar)
    Place pickPlace();                  //Selecciona donde quiere ir de una lista de todos los lugares 
}
