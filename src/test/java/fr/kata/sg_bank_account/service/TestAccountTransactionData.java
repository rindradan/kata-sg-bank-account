package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestAccountTransactionData {

    static List<AccountTransaction> generateAccountTransactions() throws ParseException {
        var user = new User(UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002"), "John Doe");
        var account = new Account(UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002"), user, 22);
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        var accountTransactions = new ArrayList<AccountTransaction>();
        accountTransactions.add(new AccountTransaction(
            UUID.fromString("844e5738-dcbb-4cff-960d-39fbe1101357"),
            formatter.parse("2022-01-15 07:10"),
            10,
            TransactionType.DEPOSIT,
            account
        ));
        accountTransactions.add(new AccountTransaction(
            UUID.randomUUID(),
            formatter.parse("2022-01-25 10:22"),
            15,
            TransactionType.WITHDRAWAL,
            account
        ));
        accountTransactions.add(new AccountTransaction(
            UUID.randomUUID(),
            formatter.parse("2022-01-26 17:49"),
            30,
            TransactionType.WITHDRAWAL,
            account
        ));

        return accountTransactions;
    }
}
