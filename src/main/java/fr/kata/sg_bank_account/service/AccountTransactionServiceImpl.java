package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.AccountTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountTransactionServiceImpl implements AccountTransactionService {

    private List<AccountTransaction> accountTransactions = new ArrayList<>();

    @Override
    public AccountTransaction createAccountTransaction(AccountTransaction accountTransaction) {
        accountTransaction.setId(UUID.randomUUID());
        accountTransactions.add(accountTransaction);
        return accountTransaction;
    }
}