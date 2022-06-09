package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.util.DBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDAO {

  public Connection getConnection() throws SQLException {
    return DBCPDataSource.getConnection();
  }
}
