package com.solvd.services.jdbcImpl;

import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.jdbcMySQLImpl.AccountDAO;
import com.solvd.services.IAccountServices;

public class AccountServiceImpl implements IAccountServices {
    private IAccountDAO accountDAO = new AccountDAO();

    @Override
    public void createAccount() {

    }

    @Override
    public void changeUsername(Account account) {

    }

    @Override
    public void changePass(Account account) {

    }

    @Override
    public void deleteAccount(Account account) {

    }

    @Override
    public void login() {

    }

    @Override
    public void accountSettings() {

    }
}
