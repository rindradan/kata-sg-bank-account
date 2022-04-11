package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountTransactionNotFoundException;
import fr.kata.sg_bank_account.model.AccountTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final List<AccountTransaction> accountTransactions = new ArrayList<>();

    @Override
    public List<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }

    @Override
    public AccountTransaction createAccountTransaction(AccountTransaction accountTransaction) {
        accountTransaction.setId(UUID.randomUUID());
        accountTransactions.add(accountTransaction);
        return accountTransaction;
    }

    @Override
    public AccountTransaction getAccountTransactionById(UUID id) throws AccountTransactionNotFoundException {
        return accountTransactions.stream().filter(accountTransaction -> id.equals(accountTransaction.getId())).findFirst().orElseThrow(AccountTransactionNotFoundException::new);
    }
}
