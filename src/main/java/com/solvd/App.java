package com.solvd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.util.Input;

public class App 
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static void main( String[] args )
    {
        //IAccountServices accountServices = new AccountServices();

        LOGGER.info("Main menu:");
        LOGGER.info("0.Exit");
        LOGGER.info("1.Register");
        LOGGER.info("2.Login");
        int option = Input.getInput().nextInt();
        switch (option) {
            case 1:
                    //accountServices.createAccount();
                break;
            case 2:
                    //accountServices.Login();
                break;
            default:
                LOGGER.info("Not valid option");
                break;
            case 0:
                
        }
        if(option != 0)main(args);
        LOGGER.info("Goodbye");
    }
}
