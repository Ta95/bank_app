/**
 * Represents a user of the bank account.
 */
package com.bankapp.account;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
