package com.solvd.dao.jdbcMySQLImpl;

import com.solvd.bin.Coordinate;
import com.solvd.bin.Fuel;
import com.solvd.bin.Place;
import com.solvd.bin.user.Account;
import com.solvd.dao.IAccountDAO;
import com.solvd.dao.ICoordinateDAO;
import com.solvd.dao.IFuelDAO;
import com.solvd.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.solvd.util.DBCPDataSource.getConnection;

public class FuelDAO implements IFuelDAO {
    private final static String SELECT_BY_FUEL_TYPE = "SELECT * FROM Fuels WHERE type=?";
    private final static String SELECT_BY_FUEL_ID = "SELECT * FROM Fuels WHERE id=?";
    private final static String DELETE_FUEL_BY_ID = "DELETE FROM Fuels WHERE id=?";
    private final static String UPDATE_FUEL_BY_ID = "UPDATE Fuels SET type=?, price=? WHERE id=?";
    private final static String INSERT_FUEL = "INSERT INTO Fuels (type,price) VALUES (?,?)";

    @Override
    public Fuel getEntityById(long id) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        try (Connection con = getConnection()) {
            pr = con.prepareStatement(SELECT_BY_FUEL_ID);
            pr.setLong(1, id);
            rs = pr.executeQuery();
            Fuel fuel = new Fuel();
            rs.next();
            fuel.setId(Integer.parseInt(rs.getString("id")));
            fuel.setType(rs.getString("type"));
            fuel.setPrice(rs.getDouble("price"));

            return fuel;
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
    public void saveEntity(Fuel entity) {
        PreparedStatement pr = null;
        try (Connection con = getConnection()) {
            pr = con.prepareStatement(INSERT_FUEL);
            pr.setString(1, entity.getType());
            pr.setDouble(2, entity.getPrice());
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
    public void updateEntity(Fuel entity) {
        PreparedStatement pr = null;
        try(Connection con = getConnection()){
            pr = con.prepareStatement(UPDATE_FUEL_BY_ID);
            pr.setString(1, entity.getType());
            pr.setDouble(2, entity.getPrice());
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
            pr = con.prepareStatement(DELETE_FUEL_BY_ID);
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
    public Fuel getEntityByType(String type) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        try (Connection con = getConnection()) {
            pr = con.prepareStatement(SELECT_BY_FUEL_TYPE);
            pr.setString(1, type);
            rs = pr.executeQuery();
            Fuel fuel = new Fuel();
            rs.next();
            fuel.setId(Integer.parseInt(rs.getString("id")));
            fuel.setType(rs.getString("type"));
            fuel.setPrice(rs.getDouble("price"));

            return fuel;
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
