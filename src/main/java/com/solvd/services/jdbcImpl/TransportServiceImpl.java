package com.solvd.services.jdbcImpl;

import com.solvd.dao.jdbcMySQLImpl.TransportDAO;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.bin.Transport;
import com.solvd.services.TransportServices;

import java.util.InputMismatchException;
import java.util.Set;

public class TransportServiceImpl implements TransportServices {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public Transport pickTransport() {
        try {
            Set<Transport> transports = new TransportDAO().getAllTransports();
            LOGGER.info("Pick a transport by ID:");
            transports.stream().forEach(transport -> {
                LOGGER.info("----------------------------------------------------------");
                LOGGER.info("ID: " + transport.getId() + transport.getType() + " - " + transport.getFuel().getType());
            });

            int j = Input.getInput().nextInt();
            return pickTransport();
        } catch (InputMismatchException ime) {
            LOGGER.info("Invalid input");
            return pickTransport();
        }
    }
}
