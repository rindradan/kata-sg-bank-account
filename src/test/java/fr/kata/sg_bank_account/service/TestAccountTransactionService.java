package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestAccountTransactionService {

    @Test
    void should_create_account_transaction() {
        AccountTransactionService accountTransactionService = new AccountTransactionServiceImpl();
        var user = new User(UUID.randomUUID(), "John Doe");
        var account = new Account(UUID.randomUUID(), user, 22);
        var accountTransaction = new AccountTransaction(new Date(), 10, TransactionType.DEPOSIT, account);
        assertNull(accountTransaction.getId());

        AccountTransaction savedAccountTransaction = accountTransactionService.createAccountTransaction(accountTransaction);

        assertNotNull(savedAccountTransaction);
        assertNotNull(savedAccountTransaction.getId());
    }
}
