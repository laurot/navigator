package com.solvd.services.jdbcImpl;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.bin.Coordinate;
import com.solvd.bin.Place;
import com.solvd.bin.Trip;
import com.solvd.bin.user.User;
import com.solvd.dao.IUserDAO;
import com.solvd.dao.jdbcMySQLImpl.CoordinateDAO;
import com.solvd.dao.jdbcMySQLImpl.UserDAO;
import com.solvd.services.AccountServices;
import com.solvd.services.UserServices;
import com.solvd.services.Navigator;
import com.solvd.util.Input;

public class UserServiceImpl implements UserServices {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public void changePosition(User user) {
        try{
            Coordinate coordinate = new Coordinate();
            LOGGER.info("Insert new X coordinate:");
            coordinate.setX(Input.getInput().nextInt());
            LOGGER.info("Insert new Y coordinate:");
            coordinate.setY(Input.getInput().nextInt());
            user.setPosition(coordinate);
            new CoordinateDAO().checkCoordinate(coordinate);
            UserDAO userDAO = new UserDAO();
            userDAO.updateEntity(user);
            LOGGER.info("Your new user position has been changed correctly");
        }catch(InputMismatchException ime){
            LOGGER.warn("Not a valid input");
            Input.getInput().next();
            changePosition(user);
        }
    }

    @Override
    public User getUser(User user) {
        IUserDAO userDAO = new UserDAO();
        user = userDAO.getEntityById(user.getId());
        return user;
    }

    @Override
    public void userMenu(User user) {
        AccountServices accountServiceImpl = new AccountServiceImpl();
        int option;
        try{
            if(user.getPosition() == null)LOGGER.info("You have to set your position before navigating");
            LOGGER.info("User menu: (" + user.getAccount().getUserName() + ")");
            LOGGER.info("1.Navigate");
            LOGGER.info("2.Set position");
            LOGGER.info("3.Account Settings");
            LOGGER.info("0.Log out");
            
            option = Input.getInput().nextInt();

                switch (option) {
                    case 1:
                        navigate(user);
                        break;
                    case 2:
                        changePosition(user);
                        break;
                    case 3:
                        accountServiceImpl.accountSettings(user.getAccount());
                        break;
                    case 0:
                        LOGGER.info("Logging out...");
                        break;
                    default:
                        LOGGER.info("Not a valid option");
                        break;
                }
            if(option != 0)userMenu(user);
        }catch(InputMismatchException ime){
            LOGGER.warn("Not a valid input");
            Input.getInput().next();
            userMenu(user);
        }
    }
    

    @Override
    public void navigate(User user) {
        Navigator navigator = new Navigator();
        Place place = new PlaceServiceImpl().pickPlace();
        Trip trip = new Trip();
        trip.setUser(user);
        trip.setTransport(new TransportServiceImpl().pickTransport());
        navigator.Navigate(trip, place.getLocation());
        double consumptionPrice = trip.getTransport().getConsumption()*trip.getTransport().getFuel().getPrice();

        LOGGER.info("The path you can take to go here is:");
        for (Coordinate coordinate : trip.getPath().getPath()) {
            LOGGER.info("( " + coordinate.getX() + ", " + coordinate.getY() + " )");
        }
        LOGGER.info("Total distance is: " +  new DecimalFormat("#.##").format(trip.getPath().getDistance()) + " kms");
        LOGGER.info("With " + trip.getTransport().getType() + ", it will take:");
        LOGGER.info("Approximately " + new DecimalFormat("#.##").format(trip.getPath().getDistance()/trip.getTransport().getSpeed()) + " hours");
        LOGGER.info("And cost approximately $" + new DecimalFormat("#.##").format(trip.getPath().getDistance()*consumptionPrice));
        LOGGER.info("--ENTER to continue--");
        new TripServiceImpl().saveTrip(trip);
        Input.getInput().nextLine();
    }
}
