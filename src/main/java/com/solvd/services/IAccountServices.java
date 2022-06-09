package com.solvd.services;

import com.solvd.bin.user.Account;

public interface IAccountServices {
    void createAccount();                   //pide los datos y crea nueva cuenta (decide si cuenta de place o user)
    void changeUsername(Account account);   //pide nuevo username y hace un update
    void changePass(Account account);       //lo de arriba con pass
    void deleteAccount(Account account);    //borra la cuenta
    void login();                        //pregunta si place o user y hace el login
}
