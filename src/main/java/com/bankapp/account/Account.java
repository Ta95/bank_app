/**
 * Represents a bank account that stores balance and transaction history.
 */
package com.bankapp.account;

import com.bankapp.db.Database;

import java.sql.*;
import java.time.LocalDateTime;

import com.bankapp.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {
    private double balance;
    private Database database;
    private User user;
    private static final Logger logger = LogManager.getLogger(Account.class);

    public Account(User user, Database database) {
        this.user = user;
        this.balance = 0.0;
        this.database = database;
    }

    /**
     * Saves the transaction in the database.
     * @param amount The amount of the transaction.
     * @param type The type of the transaction (Deposit or Withdrawal).
     */
    public void saveTransaction(double amount, String type) {
        String insertSQL = "INSERT INTO TRANSACTION (date, amount, type) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = database.getConnection().prepareStatement(insertSQL)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setDouble(2, amount);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error saving transaction: " + e.getMessage());
        }
    }


    /**
     * Prints the account statement.
     */
    public void printStatement() {
        String query = "SELECT * FROM TRANSACTION";
        double balance = 0.0;
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("********** Date | Operation | Amount **********");

            while (rs.next()) {
                Timestamp date = rs.getTimestamp("date");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                System.out.println(DateUtils.formatDate(date) + " | " + type + " | " + amount);

                if (type.equalsIgnoreCase("deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("withdrawal")) {
                    balance -= amount;
                }
            }
            System.out.println("********** Current Balance ********** : " + balance);

        } catch (SQLException e) {
            logger.error("Error printing statement: " + e.getMessage());
        }
    }


    // Getters and Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }
}
