package com.solvd.services;

import com.solvd.bin.user.User;

public interface UserServices {
    void changePosition(User user);         //cambia la posicion actual(si ya existe solo cambia el position_id, sino la crea primero)
    User getUser(User user);                //trae los datos del usuario cuando inicia sesion
    void userMenu(User user);               //opciones de user (cambiar posicion, cambiar username o pass, borrar cuenta, Navegar)
    
    void navigate(User user);               //prepara todo para la clase Navigate (crea un Trip con User, y Transport seleccionado)
                                            // y despues de mostrar el camino, guarda el trip
}
