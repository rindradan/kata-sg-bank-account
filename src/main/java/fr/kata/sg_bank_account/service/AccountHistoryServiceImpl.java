package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.dto.AccountHistory;
import fr.kata.sg_bank_account.dto.AccountHistoryItem;
import fr.kata.sg_bank_account.exception.AccountHistoryFailedException;
import fr.kata.sg_bank_account.exception.AccountNotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public class AccountHistoryServiceImpl implements AccountHistoryService {

    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;

    public AccountHistoryServiceImpl(AccountService accountService, AccountTransactionService accountTransactionService) {
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    @Override
    public AccountHistory getAccountHistoryByUser(UUID userId) throws AccountHistoryFailedException {
        try {
            var account = accountService.getAccountByUserId(userId);
            var accountTransactions = accountTransactionService.getAccountTransactionByUserId(userId);
            var accountHistories = new ArrayList<AccountHistoryItem>();
            for (var transaction : accountTransactions) {
                accountHistories.add(new AccountHistoryItem(transaction.getId(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionType()));
            }
            return new AccountHistory(account, accountHistories);

        } catch (AccountNotFoundException e) {
            throw new AccountHistoryFailedException("Account history failed because user: "+ userId +" does not have account", e);
        }
    }
}
