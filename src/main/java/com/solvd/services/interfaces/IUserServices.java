package com.solvd.services.interfaces;

import com.solvd.bin.user.User;

public interface IUserServices {
    void changePosition(User user);         //cambia la posicion actual(si ya existe solo cambia el position_id, sino la crea primero)
    User getUser();                         //trae los datos del usuario
    boolean authenticatePass();             //autentifica user y pass 
}
