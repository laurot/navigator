package com.solvd.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DBPropertiesUtil {
  private static Properties properties = new Properties();
  public static DBPropertiesUtil INSTANCE = new DBPropertiesUtil();
  private static final Logger LOGGER = LogManager.getLogger(DBPropertiesUtil.class);

  private DBPropertiesUtil() {
    try {
      properties.load(new FileReader(new File("C:\\Users\\cocob\\IdeaProjects\\Solvd-laba-DB\\src\\main\\resources\\db.properties")));
    } catch (IOException e) {
      LOGGER.error("The properties file was not read properly", e);
    }
  }

  public static DBPropertiesUtil getInstance() {
    return INSTANCE;
  }

  public String getString(String key) {
    return properties.getProperty(key);
  }

  public int getInt(String key) {
    return Integer.parseInt(properties.getProperty(key));
  }
}
