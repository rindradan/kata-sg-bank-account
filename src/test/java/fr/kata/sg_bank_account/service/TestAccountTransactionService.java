package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountTransactionNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_get_account_transaction_by_id() throws ParseException, AccountTransactionNotFoundException {
        AccountTransactionService accountTransactionService = new AccountTransactionServiceImpl();
        accountTransactionService.getAccountTransactions().addAll(TestAccountTransactionData.generateAccountTransactions());

        var accountTransaction = accountTransactionService.getAccountTransactionById(UUID.fromString("844e5738-dcbb-4cff-960d-39fbe1101357"));
        assertNotNull(accountTransaction);

        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        var date = formatter.parse("2022-01-15 07:10");
        assertEquals(date, accountTransaction.getDate());
        assertEquals(TransactionType.DEPOSIT, accountTransaction.getTransactionType());
        assertEquals(10, accountTransaction.getAmount());
        assertEquals(UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002"), accountTransaction.getAccount().getId());
    }

    @Test
    void should_get_account_transaction_by_id_fail_when_not_found() {
        AccountTransactionService accountTransactionService = new AccountTransactionServiceImpl();
        assertThrows(AccountTransactionNotFoundException.class,
            () -> accountTransactionService.getAccountTransactionById(UUID.fromString("844e5738-dcbb-4cff-960d-39fbe1101357")));
    }

    @Test
    void should_get_account_transaction_by_user_id() throws ParseException {
        AccountTransactionService accountTransactionService = new AccountTransactionServiceImpl();
        accountTransactionService.getAccountTransactions().addAll(TestAccountTransactionData.generateAccountTransactions());

        var accountTransactions = accountTransactionService.getAccountTransactionByUserId(UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002"));
        assertFalse(accountTransactions.isEmpty());
        assertEquals(2, accountTransactions.size());
        assertEquals(UUID.fromString("844e5738-dcbb-4cff-960d-39fbe1101357"), accountTransactions.get(0).getId());
        assertEquals(UUID.fromString("369aea4e-5453-47a5-9557-f0850650ece7"), accountTransactions.get(1).getId());
    }
}
