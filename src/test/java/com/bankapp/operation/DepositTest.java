package com.bankapp.operation;

import com.bankapp.account.Account;
import com.bankapp.account.User;
import com.bankapp.db.Database;
import com.bankapp.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepositTest {
    private Account account;
    private Deposit deposit;

    @BeforeEach
    void setUp() {
        account = new Account(new User("Test User", "test@example.com"), new Database());
        deposit = new Deposit(account);
    }

    @Test
    void testDepositSuccess() throws InvalidAmountException {
        deposit.execute(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void testDepositInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> deposit.execute(-100));
    }
}
