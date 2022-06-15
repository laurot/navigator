package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.user.User;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.IUserDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO implements IUserDAO {
  private final static String SELECT_BY_USER_ID = "SELECT * FROM users WHERE id = ?";
  private final static String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
  private final static String UPDATE_USER_BY_ID = "UPDATE users SET position_id = ?, account_id = ? WHERE id=?";
  private final static String INSERT_USER = "INSERT INTO users (account_id, position_id) VALUES (?,?)";

  @Override
  public User getEntityById(long id) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_USER_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      User user = new User();
      rs.next();
      user.setId(Integer.parseInt(rs.getString("id")));

      return user;
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
  public void saveEntity(User entity) {
    PreparedStatement pr = null;
    IAccountDAO accountDAO = new AccountDAO();
    try (Connection con = getConnection()) {
      new CoordinateDAO().checkCoordinate(entity.getPosition());
      pr = con.prepareStatement(INSERT_USER);
      accountDAO.saveEntity(entity.getAccount());
      pr.setInt(1, entity.getAccount().getId());
      pr.setInt(2, entity.getPosition().getId());
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
  public void updateEntity(User entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_USER_BY_ID);
      pr.setInt(1, entity.getPosition().getId());
      pr.setInt(2, entity.getAccount().getId());
      pr.setLong(3, entity.getId());
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
  public void deleteEntityById(long id) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(DELETE_USER_BY_ID);
      pr.setLong(1, id);
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
}
