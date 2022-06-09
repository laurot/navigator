package com.solvd.util;

import com.solvd.exceptions.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
  private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(5);
  private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
  private final static String URL = DBPropertiesUtil.getInstance().getString(IDBConstants.URL);
  private final static String USER_NAME = DBPropertiesUtil.getInstance().getString(IDBConstants.USER_NAME);
  private final static String PASSWORD = DBPropertiesUtil.getInstance().getString((IDBConstants.PASSWORD));
  private final static int MAX_CONNECTIONS = DBPropertiesUtil.getInstance().getInt(IDBConstants.MAX_CONNECTIONS);
  private final static String DRIVER = DBPropertiesUtil.getInstance().getString(IDBConstants.DRIVER);
  private int createdConnections = 0;
  private final static ConnectionPool INSTANCE = new ConnectionPool();

  private ConnectionPool(){
    try {
      Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
      throw new ConnectionException("Driver was not loaded properly" + e);
    }
  }

  public static ConnectionPool getInstance() {
    return INSTANCE;
  }

  public Connection getConnection() {
    if(!connections.isEmpty()) {
      return connections.remove();
    }
    if(createdConnections < MAX_CONNECTIONS) {
      return createConnection();
    } else {
      throw new ConnectionException("There is no connections available!");
    }
  }

  public void returnConnection(Connection connection) {
    connections.add(connection);
  }

  private Connection createConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
      createdConnections+= 1;
    } catch (SQLException e) {
      LOGGER.error("There was a problem creating the connection", e);
    }
    return connection;
  }
}
