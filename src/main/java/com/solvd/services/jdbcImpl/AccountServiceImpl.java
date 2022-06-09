package com.solvd.services.jdbcImpl;

import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.jdbcMySQLImpl.AccountDAO;
import com.solvd.services.IAccountServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        accountDAO.updateEntity(account.getId(), account);
        }



    @Override
    public void changePass(Account account) {
        LOGGER.info("Please insert your new Password: ");
        account.setPassword(Input.getInput().nextLine());
        accountDAO.updateEntity(account.getId(), account);
    }

    @Override
    public void deleteAccount(long id) {
        accountDAO.deleteEntityById(id);
    }

    @Override
    public void login() {
        LOGGER.info("Please select the type of account: ");
        LOGGER.info("1. User");
        LOGGER.info("2. Place");
        int option = Input.getInput().nextInt();
        switch (option){
            case 1:
                LOGGER.info("This is an user account, insert your User name and Password: ");
                break;
            case 2:
                LOGGER.info("This is a place account, insert your User name and Password: ");
                break;
            default:
                LOGGER.info("Not valid option");
                break;
        }
        LOGGER.info(option);
    }

    @Override
    public void accountSettings(Account account) {
        LOGGER.info("Please select what you want to do: ");
        LOGGER.info("1. Change User name");
        LOGGER.info("2. Change Password");
        LOGGER.info("3. Delete Account");
        int option = Input.getInput().nextInt();
        switch (option){
            case 1:
                LOGGER.info("Please insert your new User name: ");
                account.setUserName(Input.getInput().nextLine());
                accountDAO.updateEntity(account.getId(), account);
                break;
            case 2:
                LOGGER.info("Please insert your new Password: ");
                account.setPassword(Input.getInput().nextLine());
                accountDAO.updateEntity(account.getId(), account);
                break;
            case 3:
                accountDAO.deleteEntityById(account.getId());
                LOGGER.info("Your account has been deleted");
                break;
            default:
                LOGGER.info("This option is not available, try again");
        }
        LOGGER.info(option);
    }
}
