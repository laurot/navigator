package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Place;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.dao.IPlaceDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaceDAO extends AbstractDAO implements IPlaceDAO {
  private final static String SELECT_BY_PLACE_ID = "SELECT * FROM Places WHERE id=?";
  private final static String DELETE_PLACE_BY_ID = "DELETE FROM Places WHERE id=?";
  private final static String UPDATE_PLACE_BY_ID = "UPDATE Places SET name=?, location_id=? WHERE id=?";
  private final static String INSERT_PLACE = "INSERT INTO Places (name,location_id) VALUES (?,?)";

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
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_PLACE);
      pr.setString(1, entity.getName());
      pr.setInt(2, entity.getLocation().getId());
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
  public void updateEntity(long id, Place entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_PLACE_BY_ID);
      pr.setString(1, entity.getName());
      pr.setInt(2, entity.getLocation().getId());
      pr.setLong(3, id);
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
}
