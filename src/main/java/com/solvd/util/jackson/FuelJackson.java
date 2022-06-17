package com.solvd.util.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.bin.Fuel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FuelJackson {
  private static final Logger LOGGER = LogManager.getLogger(FuelJackson.class);
  private final static FuelJackson INSTANCE = new FuelJackson();

  private FuelJackson() {
  }

  public List<Fuel> serializedFuel() {
    String path = "src/main/resources/fuel.json";
    ObjectMapper om = new ObjectMapper();
    try {
      JavaType type = om.getTypeFactory().constructCollectionType(List.class, Fuel.class);
      return om.readValue(new File(path), type);
    } catch (IOException e) {
      e.printStackTrace();
      LOGGER.info("There was an error while des serializing the fuel", e);
      throw new RuntimeException(e);
    }
  }

  public static FuelJackson getInstance() {
    return INSTANCE;
  }

}
