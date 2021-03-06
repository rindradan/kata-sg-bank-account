package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountTransactionNotFoundException;
import fr.kata.sg_bank_account.model.AccountTransaction;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final List<AccountTransaction> accountTransactions;

    public AccountTransactionServiceImpl(List<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

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

    @Override
    public List<AccountTransaction> getAccountTransactionByUserId(UUID userId) {
        return accountTransactions.stream()
            .filter(accountTransaction -> userId.equals(accountTransaction.getAccount().getUser().getId()))
            .sorted(Comparator.comparing(AccountTransaction::getDate))
            .toList();
    }
}
