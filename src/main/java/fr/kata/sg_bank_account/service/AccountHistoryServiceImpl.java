package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.dto.AccountHistory;
import fr.kata.sg_bank_account.dto.AccountHistoryItem;
import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public class AccountHistoryServiceImpl implements AccountHistoryService {

    private final UserService userService;
    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;

    public AccountHistoryServiceImpl(UserService userService, AccountService accountService, AccountTransactionService accountTransactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    @Override
    public AccountHistory getAccountHistoryByUser(UUID userId) throws AccountNotFoundException, UserNotFoundException {
        var user = userService.getUser(userId);
        var account = accountService.getAccountByUserId(user.getId());
        var accountTransactions = accountTransactionService.getAccountTransactionByUserId(user.getId());
        var accountHistories = new ArrayList<AccountHistoryItem>();
        for (var transaction : accountTransactions) {
            accountHistories.add(new AccountHistoryItem(transaction.getId(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionType()));
        }
        return new AccountHistory(account, accountHistories);
    }
}
