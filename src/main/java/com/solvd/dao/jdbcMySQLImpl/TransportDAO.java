package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Fuel;
import com.solvd.bin.Place;
import com.solvd.bin.Transport;
import com.solvd.bin.user.Account;
import com.solvd.dao.*;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TransportDAO extends AbstractDAO implements ITransportDAO {
  private final static String SELECT_BY_TRANSPORT_ID = "SELECT * FROM Transports WHERE id=?";
  private final static String SELECT_ALL_TRANSPORTS = "SELECT * FROM Transports";
  private final static String DELETE_TRANSPORT_BY_ID = "DELETE FROM Transports WHERE id=?";
  private final static String UPDATE_TRANSPORT_BY_ID = "UPDATE Transports SET type=?, speed=?, consumption=? WHERE id=?";
  private final static String INSERT_TRANSPORT = "INSERT INTO Transports (type,speed,consumption) VALUES (?,?,?)";

  @Override
  public Transport getEntityById(long id) {
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_BY_TRANSPORT_ID);
      pr.setLong(1, id);
      rs = pr.executeQuery();
      Transport transport = new Transport();
      rs.next();
      transport.setId(Integer.parseInt(rs.getString("id")));
      transport.setType(rs.getString("type"));
      transport.setSpeed(rs.getInt("speed"));
      transport.setConsumption(rs.getDouble("consumption"));

      return transport;
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
  public void saveEntity(Transport entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(INSERT_TRANSPORT);
      pr.setString(1, entity.getType());
      pr.setInt(2, entity.getSpeed());
      pr.setDouble(3, entity.getConsumption());
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
  public void updateEntity(Transport entity) {
    PreparedStatement pr = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(UPDATE_TRANSPORT_BY_ID);
      pr.setString(1, entity.getType());
      pr.setInt(2, entity.getSpeed());
      pr.setDouble(3, entity.getConsumption());
      pr.setLong(4, entity.getId());
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
      pr = con.prepareStatement(DELETE_TRANSPORT_BY_ID);
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
  public Set<Transport> getAllTransports() {
    Set<Transport> transports = new TreeSet<Transport>();
    PreparedStatement pr = null;
    ResultSet rs = null;
    try (Connection con = getConnection()) {
      pr = con.prepareStatement(SELECT_ALL_TRANSPORTS);
      rs = pr.executeQuery();
      Transport transportAux = new Transport();

      while (rs.next()) {

        transportAux.setId(rs.getInt("id"));
        transportAux.setType(rs.getString("type"));
        transportAux.setSpeed(Integer.parseInt(rs.getString("speed")));
        transportAux.setConsumption(Double.parseDouble(rs.getString("consumption")));
        int fuelIdAux = (Integer.parseInt(rs.getString("fuel_id")));
        IFuelDAO fuelDAO = new FuelDAO();
        Fuel fuelAux = fuelDAO.getEntityById(fuelIdAux);
        transportAux.setFuel(fuelAux);

        transports.add(transportAux);
      }

      rs.close();
    } catch (Exception e) {
      System.out.println(e);
    }

    return transports;
  }
}
