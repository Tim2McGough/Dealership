package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtils {
    private static BasicDataSource dataSource;

    // Static block to configure the DataSource
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership"); // Replace with your DB details
        dataSource.setUsername("root"); // Replace with your MySQL username
        dataSource.setPassword("yearup"); // Replace with your MySQL password
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMinIdle(5); // Minimum idle connections in the pool
        dataSource.setMaxIdle(10); // Maximum idle connections in the pool
        dataSource.setMaxOpenPreparedStatements(100);
    }

    // Method to get a connection from the DataSource
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Method to insert a vehicle into the database
    public static void insertVehicle(String type, String color, int passengers, int cargo, int fuel, boolean cleanliness) {
        String sql = "INSERT INTO vehicles (type, color, number_of_passengers, cargo_capacity, fuel_capacity, cleanliness) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            statement.setString(2, color);
            statement.setInt(3, passengers);
            statement.setInt(4, cargo);
            statement.setInt(5, fuel);
            statement.setBoolean(6, cleanliness);
            statement.executeUpdate();
            System.out.println("Vehicle inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// method for dealerships
    public static void insertDealership(String name, String address, String phoneNumber) {
        String sql = "INSERT INTO dealership (name, address, phone_number) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phoneNumber);
            statement.executeUpdate();
            System.out.println("Dealership inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //method for Sales Contract
    public static void insertSalesContract(int vehicleId, int dealershipId, String buyerName, double salePrice, String saleDate) {
        String sql = "INSERT INTO sales_contract (vehicle_id, dealership_id, buyer_name, sale_price, sale_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleId);
            statement.setInt(2, dealershipId);
            statement.setString(3, buyerName);
            statement.setDouble(4, salePrice);
            statement.setDate(5, java.sql.Date.valueOf(saleDate)); // Expecting YYYY-MM-DD format
            statement.executeUpdate();
            System.out.println("Sales contract inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Method for lease contract
    public static void insertLeaseContract(int vehicleId, int dealershipId, String lesseeName, double leaseAmount, String leaseStartDate, String leaseEndDate) {
        String sql = "INSERT INTO lease_contract (vehicle_id, dealership_id, lessee_name, lease_amount, lease_start_date, lease_end_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleId);
            statement.setInt(2, dealershipId);
            statement.setString(3, lesseeName);
            statement.setDouble(4, leaseAmount);
            statement.setDate(5, java.sql.Date.valueOf(leaseStartDate)); // Expecting YYYY-MM-DD format
            statement.setDate(6, java.sql.Date.valueOf(leaseEndDate)); // Expecting YYYY-MM-DD format
            statement.executeUpdate();
            System.out.println("Lease contract inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
