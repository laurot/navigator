package com.solvd.services.jdbcImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Place;
import com.solvd.dao.IPlaceDAO;
import com.solvd.dao.jdbcMySQLImpl.PlaceDAO;
import com.solvd.services.AccountServices;
import com.solvd.services.PlaceServices;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaceServiceImpl implements PlaceServices {
    private Logger LOGGER = LogManager.getLogger(PlaceServiceImpl.class);
    private IPlaceDAO placeDAO = new PlaceDAO();

    @Override
    public void changeLocation(Place place) {
        try{
            Coordinate coordinate = new Coordinate();
            LOGGER.info("Insert new X coordinate:");
            coordinate.setX(Input.getInput().nextInt());
            LOGGER.info("Insert new Y coordinate:");
            coordinate.setY(Input.getInput().nextInt());
            place.setLocation(coordinate);
            LOGGER.info("Your new place location has been changed correctly");
        }catch(InputMismatchException ime){
            LOGGER.warn("Not a valid input");
Input.getInput().next();
            changeLocation(place);
        }
    }

    @Override
    public void changeName(Place place) {
        LOGGER.info("Please insert the new name of the place: ");
        place.setName(Input.getInput().nextLine());
        placeDAO.updateEntity(place);
        LOGGER.info("The new name of the place has been changed correctly");
    }

    @Override
    public void placeMenu(Place place) {
        AccountServices accountServiceImpl = new AccountServiceImpl();
        int option;
        try{
            LOGGER.info("Place menu: (" + place.getName() + ")");
            LOGGER.info("1.Change name");
            LOGGER.info("2. Change Location");
            LOGGER.info("3. Account Settings");
            LOGGER.info("4. Delete Place");
            LOGGER.info("0.Log out");

            option = Input.getInput().nextInt();

            switch (option) {
                case 1:
                    changeName(place);
                    break;
                case 2:
                    changeLocation(place);
                    break;
                case 3:
                    accountServiceImpl.accountSettings(place.getAccount());
                    break;
                case 4:
                    placeDAO.deleteEntityById(place.getId());
                    break;
                case 0:
                    LOGGER.info("Logging out...");
                    break;
                default:
                    LOGGER.info("Not a valid option");
                    break;
            }
            if(option != 0)placeMenu(place);
        }catch(InputMismatchException ime){
            LOGGER.warn("Not a valid input");
Input.getInput().next();
            placeMenu(place);
        }
    }

    @Override
    public Place findPlaceByName(String name) {
        return placeDAO.getEntityByName(name);
    }

    @Override
    public Place pickPlace() {
        try {
            Set<Place> places = new PlaceDAO().getAllPlaces();
            LOGGER.info("Pick a place:");
            places.stream().forEach(place -> {
                LOGGER.info("----------------------------------------------------------");
                LOGGER.info(place.getId() + ". " + place.getName() + " -  " + place.getLocation().toString());
            });
            String placeName = Input.getInput().nextLine();
            Place place = places.stream().filter(p -> p.getName().equals(placeName)).collect(Collectors.toList()).remove(0);

            return place;
        } catch (InputMismatchException ime) {
            LOGGER.info("Invalid input");
            return pickPlace();
        }
    }
}
