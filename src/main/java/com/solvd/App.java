package com.solvd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.util.Input;

public class App 
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main( String[] args )
    {
        LOGGER.info("Main menu:");
        LOGGER.info("0.Exit");
        LOGGER.info("1.Sign In");
        LOGGER.info("2.Login user");
        LOGGER.info("3.Login place");
        Input.getInput().nextInt();
    }
}
