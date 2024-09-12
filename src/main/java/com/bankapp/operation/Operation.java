/**
 * Interface representing an operation on a bank account.
 */
package com.bankapp.operation;

import com.bankapp.exceptions.InvalidAmountException;
import com.bankapp.exceptions.InsufficientBalanceException;

public interface Operation {
    void execute(double amount) throws InvalidAmountException, InsufficientBalanceException;
}
