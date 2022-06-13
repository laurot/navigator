package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Transport;
import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CoordinateDAO extends AbstractDAO implements ICoordinateDAO {
  private final static String SELECT_BY_COORDINATE_ID = "SELECT * FROM Coordinates WHERE id=?";
  private final static String SELECT_ALL_COORDINATES = "SELECT * FROM Coordinates";
  private final static String DELETE_COORDINATE_BY_ID = "DELETE FROM Coordinates WHERE id=?";
  private final static String UPDATE_COORDINATE_BY_ID = "UPDATE Coordinates SET x=?, y=? WHERE id=?";
  private final static String INSERT_COORDINATE = "INSERT INTO Coordinates (x,y) VALUES (?,?)";

  @Override
  public Coordinate getEntityById(long id) throws DAOException {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_COORDINATE_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Coordinate coordinate = new Coordinate();
      rs.next();
      coordinate.setId(Integer.parseInt(rs.getString("id")));
      coordinate.setX(Integer.parseInt(rs.getString("x")));
      coordinate.setY(Integer.parseInt(rs.getString("y")));

      return coordinate;
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
  public void saveEntity(Coordinate entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_COORDINATE);
      pr.setInt(1, entity.getX());
      pr.setInt(2, entity.getY());
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
  public void updateEntity(Coordinate entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_COORDINATE_BY_ID);
      pr.setInt(1, entity.getX());
      pr.setInt(2, entity.getY());
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
      pr = con.prepareStatement(DELETE_COORDINATE_BY_ID);
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
  public Set<Coordinate> getAllCoordinates() {
    Set<Coordinate> coordinates = new TreeSet<Coordinate>();
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_ALL_COORDINATES);
      rs = pr.executeQuery();
      Coordinate coordinateAux = new Coordinate();

      while (rs.next()) {

        coordinateAux.setId(rs.getInt("id"));
        coordinateAux.setX(Integer.parseInt(rs.getString("x")));
        coordinateAux.setY(Integer.parseInt(rs.getString("y")));

        coordinates.add(coordinateAux);
      }

      rs.close();
    } catch (Exception e) {
      System.out.println(e);
    }

    return coordinates;
  }
}
