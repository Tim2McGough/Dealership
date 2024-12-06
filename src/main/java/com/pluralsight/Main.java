package com.pluralsight;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        // Insert a dealership
        DatabaseUtils.insertDealership("Yearup Dealership", "123 Main St, Springfield, IL", "555-123-4567");

        // Insert a sales contract
        DatabaseUtils.insertSalesContract(1, 1, "John Doe", 25000.00, "2023-12-01");

        // Insert a lease contract
        DatabaseUtils.insertLeaseContract(1, 1, "Jane Smith", 1200.00, "2023-12-01", "2024-12-06");
    }
}

