/**
 * Custom exception thrown when an invalid amount is provided.
 */
package com.bankapp.exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
