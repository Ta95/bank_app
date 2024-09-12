/**
 * Custom exception thrown when the account has insufficient balance.
 */
package com.bankapp.exceptions;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
