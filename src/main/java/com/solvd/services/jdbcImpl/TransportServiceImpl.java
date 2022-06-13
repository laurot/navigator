package com.solvd.services.jdbcImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.bin.Transport;
import com.solvd.services.TransportServices;

public class TransportServiceImpl implements TransportServices {

    private static final Logger LOGGER = LogManager.getLogger();
    
    @Override
    public Transport pickTransport() {
        /*try {
            Set<Transport> transports = new TransportDAO().getAllTransports();
            LOGGER.info("Pick a transport:");
            for (int i = 0; i < transports.size()-1; i++) {
                LOGGER.info("----------------------------------------------------------");
                LOGGER.info(i+1 + ". " + transports.get(i).getType() + " - " + transports.get(i).getFuel().getType());
            }
            int j = Input.getInput().nextInt();
            if(j > 0 && j < transports.size()) return transports.get(j-1);
            return pickTransport();
        } catch (InputMismatchException ime) {
            LOGGER.info("Invalid input");
            return pickTransport();
        }*/
        return null;
    }
}
