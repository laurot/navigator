package com.solvd.dao;

import com.solvd.bin.Place;
import com.solvd.bin.user.Account;
import com.solvd.bin.user.User;

public interface IAccountDAO extends IBaseDAO<Account>{
    User authenticateUser(Account account);
    Place authenticatePlace(Account account);
}
