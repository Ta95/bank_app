package com.bankapp.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to manage the in-memory database using H2.
 */
public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);
    private static Connection connection;

    public Database() {
        try {
            // Establish connection to the H2 in-memory database
            connection = DriverManager.getConnection("jdbc:h2:mem:bankappdb", "sa", "");
            createTransactionTable();
        } catch (SQLException e) {
            logger.error("Error connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Create the TRANSACTION table in the in-memory database.
     */
    private void createTransactionTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS TRANSACTION ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "date TIMESTAMP, "
                + "type VARCHAR(255), "
                + "amount DOUBLE, "
                + "balance DOUBLE"
                + ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            logger.info("Transaction table created successfully.");
        } catch (SQLException e) {
            logger.error("Error creating the transaction table: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
