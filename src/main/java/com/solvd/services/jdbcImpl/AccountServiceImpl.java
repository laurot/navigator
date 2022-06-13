package com.solvd.services.jdbcImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Place;
import com.solvd.bin.user.Account;
import com.solvd.bin.user.User;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.jdbcMySQLImpl.AccountDAO;
import com.solvd.dao.jdbcMySQLImpl.PlaceDAO;
import com.solvd.dao.jdbcMySQLImpl.UserDAO;
import com.solvd.exceptions.InvalidAccountException;
import com.solvd.services.AccountServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

public class AccountServiceImpl implements AccountServices {
    private Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);
    private IAccountDAO accountDAO = new AccountDAO();

    @Override
    public void createAccount() {
        Account account = new Account();
    try {
        LOGGER.info("Please insert your Username: ");
        account.setUserName(Input.getInput().nextLine());
        LOGGER.info("Please insert your password: ");
        account.setPassword(Input.getInput().nextLine());
        LOGGER.info("What kind of account do you want to make?: ");
        LOGGER.info("1. User account ");
        LOGGER.info("2. Place account ");
        LOGGER.info("0. Cancel");
        switch (Input.getInput().nextInt()) {
            case 1:
                new UserDAO().saveEntity(new User(account));
                break;
            case 2:
                Place place = new Place();
                Coordinate coordinate = new Coordinate();
                LOGGER.info("Please insert the name of the place: ");
                place.setName(Input.getInput().nextLine());
                LOGGER.info("Please insert the position:");
                LOGGER.info("X:");
                coordinate.setX(Input.getInput().nextInt());
                LOGGER.info("Y:");
                coordinate.setY(Input.getInput().nextInt());
                place.setLocation(coordinate);
                place.setAccount(account);
                new PlaceDAO().saveEntity(place);
                break; 
            default:
                break;
        }
        
    } catch (InputMismatchException ime) {
        LOGGER.warn("Not a valid input");
        Input.getInput().next();
        createAccount();
    }
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
                    LOGGER.info("This is an user account, insert your Username:");
                    account.setUserName(Input.getInput().nextLine());
                    LOGGER.info("This is an user account, insert your password:");
                    account.setPassword(Input.getInput().nextLine());
                    accountDAO.authenticateUser(account);
                    new UserServiceImpl().userMenu(new User(account));
                case 2:
                    LOGGER.info("This is a place account, insert your Username:");
                    account.setUserName(Input.getInput().nextLine());
                    LOGGER.info("This is an user account, insert your password:");
                    account.setPassword(Input.getInput().nextLine());
                    new PlaceServiceImpl().placeMenu(accountDAO.authenticatePlace(account));
                case 0:
                    LOGGER.info("Returning...");
                    return;
                default:
                    LOGGER.info("Not valid option");
                    break;
            }
            if (option != 0) login();
        } catch (InputMismatchException ime) {
            LOGGER.warn("Not a valid input");
Input.getInput().next();
            login();
        } catch (InvalidAccountException iae){
            LOGGER.warn(iae.getMessage());
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
Input.getInput().next();
            accountSettings(account);
        }
    }
}
