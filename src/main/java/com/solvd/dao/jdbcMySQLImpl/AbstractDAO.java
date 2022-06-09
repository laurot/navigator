package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.util.ConnectionPool;

import java.sql.Connection;

public class AbstractDAO {

  public Connection getConnection() { return ConnectionPool.getInstance().getConnection(); }

  public void returnConnection(Connection connection) {
    ConnectionPool.getInstance().returnConnection(connection);
  }
}
