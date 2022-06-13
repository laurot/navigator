package com.solvd.dao;

import com.solvd.bin.Place;
import com.solvd.bin.user.Account;

public interface IAccountDAO extends IBaseDAO<Account>{
    Account authenticateUser(Account account);
    Place authenticatePlace(Account account);
}
