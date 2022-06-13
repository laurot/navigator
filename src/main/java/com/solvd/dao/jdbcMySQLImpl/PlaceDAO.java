package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Place;
import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.dao.IPlaceDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PlaceDAO extends AbstractDAO implements IPlaceDAO {
  private final static String SELECT_BY_PLACE_ID = "SELECT * FROM Places WHERE id=?";
  private final static String SELECT_BY_PLACE_NAME = "SELECT * FROM Places WHERE name=?";
  private final static String SELECT_ALL_PLACES = "SELECT * FROM Places";
  private final static String DELETE_PLACE_BY_ID = "DELETE FROM Places WHERE id=?";
  private final static String UPDATE_PLACE_BY_ID = "UPDATE Places SET name=?, location_id=? WHERE id=?";
  private final static String INSERT_PLACE = "INSERT INTO Places (name,location_id,account_id) VALUES (?,?,?)";

  @Override
  public Place getEntityById(long id) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_PLACE_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Place place = new Place();
      rs.next();
      place.setId(Integer.parseInt(rs.getString("id")));
      place.setName(rs.getString("name"));

      return place;
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
  public void saveEntity(Place entity) {
    PreparedStatement pr = null;
    IAccountDAO accountDAO = new AccountDAO();
    ICoordinateDAO coordinateDAO = new CoordinateDAO();
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_PLACE);
      accountDAO.saveEntity(entity.getAccount());
      coordinateDAO.saveEntity(entity.getLocation());
      pr.setString(1, entity.getName());
      pr.setInt(2, entity.getLocation().getId());
      pr.setInt(3, entity.getAccount().getId());
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
  public void updateEntity(Place entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_PLACE_BY_ID);
      pr.setString(1, entity.getName());
      pr.setInt(2, entity.getLocation().getId());
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
      pr = con.prepareStatement(DELETE_PLACE_BY_ID);
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
  public Set<Place> getAllPlaces() {
    Set<Place> places = new TreeSet<Place>();
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_ALL_PLACES);
      rs = pr.executeQuery();
      Place placeAux = new Place();

      while (rs.next()) {

        placeAux.setId(rs.getInt("id"));
        placeAux.setName(rs.getString("name"));
        int cordIdAux = (Integer.parseInt(rs.getString("coordinate_id")));
        ICoordinateDAO cordDAO = new CoordinateDAO();
        Coordinate cordAux = cordDAO.getEntityById(cordIdAux);
        placeAux.setLocation(cordAux);
        int accountIdAux = (Integer.parseInt(rs.getString("account_id")));
        IAccountDAO accDAO = new AccountDAO();
        Account accountAux = accDAO.getEntityById(accountIdAux);
        placeAux.setAccount(accountAux);

        places.add(placeAux);
      }

      rs.close();
    } catch (Exception e) {
      System.out.println(e);
    }

    return places;
  }

  @Override
  public Place getEntityByName(String name) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_PLACE_NAME);
      pr.setString(1, name);
      rs = pr.executeQuery();
      Place place = new Place();
      rs.next();
      place.setId(Integer.parseInt(rs.getString("id")));
      place.setName(rs.getString("name"));
      int cordId = (Integer.parseInt(rs.getString("coordinate_id")));
      ICoordinateDAO cordDAO = new CoordinateDAO();
      Coordinate cord = cordDAO.getEntityById(cordId);
      place.setLocation(cord);
      int accountId = (Integer.parseInt(rs.getString("account_id")));
      IAccountDAO accDAO = new AccountDAO();
      Account account = accDAO.getEntityById(accountId);
      place.setAccount(account);

      return place;
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
