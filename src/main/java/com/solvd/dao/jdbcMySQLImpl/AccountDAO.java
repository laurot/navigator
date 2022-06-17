package com.solvd.dao.jdbcMySQLImpl;


import com.solvd.bin.Place;
import com.solvd.bin.user.Account;
import com.solvd.bin.user.User;
import com.solvd.dao.IAccountDAO;
import com.solvd.exceptions.DAOException;
import com.solvd.exceptions.InvalidAccountException;
import java.sql.*;

public class AccountDAO extends AbstractDAO implements IAccountDAO {
  private final static String SELECT_BY_ACCOUNT_ID = "SELECT * FROM Accounts WHERE id=?";
  private final static String DELETE_ACCOUNT_BY_ID = "DELETE FROM Accounts WHERE id=?";
  private final static String UPDATE_ACCOUNT_BY_ID = "UPDATE Accounts SET userName=?, pass=? WHERE id=?";
  private final static String INSERT_ACCOUNT = "INSERT INTO Accounts (username,pass) VALUES (?,?)";
  private final static String AUTHENTICATE_USER = "SELECT a.id as account_id, u.position_id as position_id, u.id as user_id FROM users u LEFT JOIN accounts a ON u.account_id = a.id WHERE a.username = ? AND a.pass = ? ";
  private final static String AUTHENTICATE_PLACE = "SELECT a.id as account_id, p.id as place_id, p.location_id as location_id, p.name as name FROM places p LEFT JOIN accounts a ON p.account_id = a.id WHERE a.username = ? AND a.pass = ? ";
  
  @Override
  public Account getEntityById(long id) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_ACCOUNT_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Account account = new Account();
      rs.next();
      account.setId(Integer.parseInt(rs.getString("id")));
      account.setUserName(rs.getString("username"));
      account.setPassword(rs.getString("pass"));

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
      pr = con.prepareStatement(INSERT_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
      pr.setString(1, entity.getUserName());
      pr.setString(2, entity.getPassword());
      int affectedRows = pr.executeUpdate();
      if(affectedRows == 0) throw new SQLException("Saving delivery failed");
      ResultSet keys = pr.getGeneratedKeys();
      if (keys.next()) {
        entity.setId(keys.getInt(1));
      }
    }catch (SQLException e) {
      throw new DAOException("There was a problem while doing the statement " + e);
    } finally {
      try {
        if (pr != null)
          pr.close();
      } catch (SQLException e) {
        throw new DAOException("Exception while closing the statement " + e);
      }
    }
  }

  @Override
  public void updateEntity(Account entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_ACCOUNT_BY_ID);
      pr.setString(1, entity.getUserName());
      pr.setString(2, entity.getPassword());
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
      pr = con.prepareStatement(DELETE_ACCOUNT_BY_ID);
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

  @Override
  public User authenticateUser(Account account) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(AUTHENTICATE_USER);
      pr.setString(1, account.getUserName());
      pr.setString(2, account.getPassword());
      rs = pr.executeQuery();
      if(rs.next()){
        account.setId(rs.getInt("account_id"));
        User user = new User();
        user.setAccount(account);
        user.setId(rs.getInt("user_id"));
        user.setPosition(new CoordinateDAO().getEntityById(rs.getInt("position_id")));
        return user;
      }else{
        throw new InvalidAccountException("Username or password is incorrect for a user");
      }
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
  public Place authenticatePlace(Account account) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    Place place = new Place();
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(AUTHENTICATE_PLACE);
      pr.setString(1, account.getUserName());
      pr.setString(2, account.getPassword());
      rs = pr.executeQuery();
      if(rs.next()){
        account.setId(rs.getInt("account_id"));
        place.setId(rs.getInt("place_id"));
        place.setAccount(account);
        place.setLocation(new CoordinateDAO().getEntityById(rs.getInt("location_id")));
        place.setName(rs.getString("name"));
        return place;
      }else{
        throw new InvalidAccountException("Username or password is incorrect for a place");
      }
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
}