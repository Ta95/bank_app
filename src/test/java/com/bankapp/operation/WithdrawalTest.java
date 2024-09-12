package com.bankapp.operation;

import com.bankapp.account.Account;
import com.bankapp.account.User;
import com.bankapp.db.Database;
import com.bankapp.exceptions.InvalidAmountException;
import com.bankapp.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WithdrawalTest {
    private Account account;
    private Withdrawal withdrawal;

    @BeforeEach
    void setUp() {
        account = new Account(new User("Test User", "test@example.com"), new Database());
        withdrawal = new Withdrawal(account);
    }

    @Test
    void testWithdrawalSuccess() throws InvalidAmountException, InsufficientBalanceException {
        account.setBalance(100);
        withdrawal.execute(50);
        assertEquals(50, account.getBalance());
    }

    @Test
    void testWithdrawalInsufficientBalance() {
        account.setBalance(50);
        assertThrows(InsufficientBalanceException.class, () -> withdrawal.execute(100));
    }

    @Test
    void testWithdrawalInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> withdrawal.execute(-50));
    }
}
