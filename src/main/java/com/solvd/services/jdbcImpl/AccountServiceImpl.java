package com.solvd.services.jdbcImpl;

import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.jdbcMySQLImpl.AccountDAO;
import com.solvd.services.IAccountServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

public class AccountServiceImpl implements IAccountServices {
    private Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);
    private IAccountDAO accountDAO = new AccountDAO();

    @Override
    public void createAccount() {
        Account account = new Account();
        accountDAO.saveEntity(account);
    }

    @Override
    public void changeUsername(Account account) {
        LOGGER.info("Please insert your new User name: ");
        account.setUserName(Input.getInput().nextLine());
        accountDAO.updateEntity(account);
        LOGGER.info("Your user name has been changed correctly");
    }


    @Override
    public void changePass(Account account) {
        LOGGER.info("Please insert your new Password: ");
        account.setPassword(Input.getInput().nextLine());
        accountDAO.updateEntity(account);
        LOGGER.info("Your password has been changed correctly");
    }

    @Override
    public void deleteAccount(long id) {
        accountDAO.deleteEntityById(id);
    }

    @Override
    public void login() {
        Account account = new Account();
        int option;
        try {
            LOGGER.info("Please select the type of account: ");
            LOGGER.info("1. User");
            LOGGER.info("2. Place");
            LOGGER.info("0. Return");
            option = Input.getInput().nextInt();
            switch (option) {
                case 1:
                    LOGGER.info("This is an user account, insert your User name and Password: ");
                    break;
                case 2:
                    LOGGER.info("This is a place account, insert your User name and Password: ");
                    break;
                case 0:
                    LOGGER.info("Returning...");
                default:
                    LOGGER.info("Not valid option");
                    break;
            }
            if (option != 0) login();
        } catch (InputMismatchException ime) {
            LOGGER.warn("Not a valid input");
            login();
        }
    }

    @Override
    public void accountSettings(Account account) {
        int option;
        try {
            LOGGER.info("Please select what you want to do: ");
            LOGGER.info("1. Change User name");
            LOGGER.info("2. Change Password");
            LOGGER.info("3. Delete Account");
            LOGGER.info("0. Return");

            option = Input.getInput().nextInt();
            switch (option) {
                case 1:
                    changeUsername(account);
                    break;
                case 2:
                    changePass(account);
                    break;
                case 3:
                    deleteAccount(account.getId());
                    break;
                case 0:
                    LOGGER.info("Returning...");
                    break;
                default:
                    LOGGER.info("This option is not available, try again");
                    break;
            }
            if (option != 0)
                accountSettings(account);
        } catch (InputMismatchException ime) {
            LOGGER.warn("Not a valid input");
            accountSettings(account);
        }
    }
}
