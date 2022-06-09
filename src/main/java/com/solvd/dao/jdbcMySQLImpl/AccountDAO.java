package com.solvd.dao.jdbcMySQLImpl;


import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends AbstractDAO implements IAccountDAO {
  private final static String SELECT_BY_ACCOUNT_ID = "SELECT * FROM Accounts WHERE id=?";
  private final static String DELETE_ACCOUNT_BY_ID = "DELETE FROM Accounts WHERE id=?";
  private final static String UPDATE_ACCOUNT_BY_ID = "UPDATE Accounts SET userName=?, password=? WHERE id=?";
  private final static String INSERT_ACCOUNT = "INSERT INTO Accounts (userName,password) VALUES (?,?)";

  @Override
  public Account getEntityById(long id) throws DAOException {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_ACCOUNT_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Account account = new Account();
      rs.next();
      account.setId(Integer.parseInt(rs.getString("id")));
      account.setUserName(rs.getString("userName"));
      account.setPassword(rs.getString("password"));

      return account;
    } catch (SQLException e) {
      throw new DAOException("There was a problem while doing the statement" + e);
    } finally {
      try {
        if (pr != null)
          pr.close();
        if (rs != null)
          rs.close();
      } catch (SQLException e) {
        throw new DAOException("Exception while closing the statement" + e);
      }
    }
  }

  @Override
  public void saveEntity(Account entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_ACCOUNT);
      pr.setString(1, entity.getUserName());
      pr.setString(2, entity.getPassword());
      pr.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was a problem while doing the statement" + e);
    } finally {
      try {
        if (pr != null)
          pr.close();
      } catch (SQLException e) {
        throw new DAOException("Exception while closing the statement" + e);
      }
    }
  }

  @Override
  public void updateEntity(long id, Account entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_ACCOUNT_BY_ID);
      pr.setString(1, entity.getUserName());
      pr.setString(2, entity.getPassword());
      pr.setLong(3, id);
      pr.execute();
    } catch (SQLException e) {
      throw new DAOException("There was a problem while doing the statement" + e);
    } finally {
      try {
        if (pr != null)
          pr.close();
      } catch (SQLException e) {
        throw new DAOException("Exception while closing the statement" + e);
      }
    }
  }

  @Override
  public void deleteEntityById(long id) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(DELETE_ACCOUNT_BY_ID);
      pr.setLong(1, id);
      pr.execute();
    } catch (SQLException e) {
      throw new DAOException("There was a problem while doing the statement" + e);
    } finally {
      try {
        if (pr != null)
          pr.close();
      } catch (SQLException e) {
        throw new DAOException("Exception while closing the statement" + e);
      }
    }
  }
}