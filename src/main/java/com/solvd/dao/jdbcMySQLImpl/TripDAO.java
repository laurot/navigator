package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Place;
import com.solvd.bin.Trip;
import com.solvd.dao.ITripDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TripDAO extends AbstractDAO implements ITripDAO {
  private final static String SELECT_BY_TRIP_ID = "SELECT * FROM Trips WHERE id=?";
  private final static String DELETE_TRIP_BY_ID = "DELETE FROM Trips WHERE id=?";
  private final static String UPDATE_TRIP_BY_ID = "UPDATE Trips SET transport_id=?, user_id=?, path_id=? WHERE id=?";
  private final static String INSERT_TRIP = "INSERT INTO Trips (transport_id, user_id, path_id) VALUES (?,?)";

  @Override
  public Trip getEntityById(long id) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_TRIP_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Trip trip = new Trip();
      rs.next();
      trip.setId(Integer.parseInt(rs.getString("id")));

      return trip;
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
  public void saveEntity(Trip entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_TRIP);
      pr.setInt(1, entity.getTransport().getId());
      pr.setInt(2, entity.getUser().getId());
      pr.setInt(2, entity.getPath().getId());
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
  public void updateEntity(long id, Trip entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_TRIP_BY_ID);
      pr.setInt(1, entity.getTransport().getId());
      pr.setInt(2, entity.getUser().getId());
      pr.setInt(2, entity.getPath().getId());
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
      pr = con.prepareStatement(DELETE_TRIP_BY_ID);
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
