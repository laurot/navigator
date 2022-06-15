package com.solvd.services.jdbcImpl;

import com.solvd.dao.jdbcMySQLImpl.TransportDAO;
import com.solvd.util.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.bin.Transport;
import com.solvd.services.TransportServices;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class TransportServiceImpl implements TransportServices {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public Transport pickTransport() {
        try {
            List<Transport> transports = new TransportDAO().getAllTransports();
            LOGGER.info("Pick a transport by Id:");
            transports.stream().forEach(transport -> {
                LOGGER.info("----------------------------------------------------------");
                LOGGER.info("Id: " + transport.getId() + " - " + transport.getType() + " - " + transport.getFuel().getType());
            });
            int i = Input.getInput().nextInt();
            Transport transport = transports.stream().filter(t -> t.getId() == i).collect(Collectors.toList()).remove(0);
            return transport;
        } catch (InputMismatchException ime) {
            LOGGER.info("Invalid input");
            return pickTransport();
        }
    }
}
