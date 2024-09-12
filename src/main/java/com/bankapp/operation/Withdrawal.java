/**
 * Represents a withdrawal operation on a bank account.
 */
package com.bankapp.operation;

import com.bankapp.account.Account;
import com.bankapp.exceptions.InvalidAmountException;
import com.bankapp.exceptions.InsufficientBalanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Withdrawal implements Operation {
    private Account account;
    private static final Logger logger = LogManager.getLogger(Withdrawal.class);

    public Withdrawal(Account account) {
        this.account = account;
    }

    /**
     * Executes the withdrawal operation.
     * @param amount The amount to withdraw.
     * @throws InvalidAmountException if the amount is less than or equal to zero.
     * @throws InsufficientBalanceException if the balance is insufficient.
     */
    @Override
    public void execute(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
        account.setBalance(account.getBalance() - amount);
        account.saveTransaction(amount, "Withdrawal");
        logger.info("Withdrawal of " + amount + " successful.");
    }
}
