package com.solvd.services.interfaces;

import com.solvd.bin.user.Account;

public interface IAccountServices {
    void createAccount();                   //pide los datos y crea nueva cuenta
    void changeUsername(Account account);   //pide nuevo username y hace un update
    void changePass(Account account);       //lo de arriba con pass
    void deleteAccount(Account account);    //borra la cuenta
    Account login();                        //pregunta si place o user y hace el login
}
