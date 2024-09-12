/**
 * Main class for the bank application. Handles deposits, withdrawals, and account statements.
 */
package com.bankapp;

import com.bankapp.account.Account;
import com.bankapp.account.User;
import com.bankapp.db.Database;
import com.bankapp.operation.Deposit;
import com.bankapp.operation.Withdrawal;
import com.bankapp.exceptions.InvalidAmountException;
import com.bankapp.exceptions.InsufficientBalanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class BankApp {
    private static final Logger logger = LogManager.getLogger(BankApp.class);

    public static void main(String[] args) {
        Database database = new Database();
        User user = new User("John Doe", "john.doe@example.com");
        Account account = new Account(user, database);

        Deposit deposit = new Deposit(account);
        Withdrawal withdrawal = new Withdrawal(account);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\nWelcome, " + user.getName() + "!");
            System.out.println("Please select an operation:");
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Print account statement");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        deposit.execute(depositAmount);
                    } catch (InvalidAmountException e) {
                        logger.error(e.getMessage());
                        System.out.println("Invalid amount: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    try {
                        withdrawal.execute(withdrawalAmount);
                    } catch (InvalidAmountException | InsufficientBalanceException e) {
                        logger.error(e.getMessage());
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    account.printStatement();
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
}
