package com.solvd.services.jdbcImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.bin.user.User;
import com.solvd.services.IUserServices;

public class UserServiceImpl implements IUserServices {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public void changePosition(User user) {

    }

    @Override
    public User getUser(User user) {
        return null;
    }

    @Override
    public boolean authenticatePass() {
        return false;
    }

    @Override
    public void userMenu(User user) {
        LOGGER.info("User menu: (" + user.getAccount().getUserName() + ")");
        LOGGER.info("1.Navigate");
        LOGGER.info("2.Set position");
        LOGGER.info("3.Account Settings");

    }

    @Override
    public void navigate(User user) {

    }
}
