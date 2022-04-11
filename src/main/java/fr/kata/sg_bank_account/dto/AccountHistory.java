package fr.kata.sg_bank_account.dto;

import fr.kata.sg_bank_account.model.Account;

import java.util.List;

public class AccountHistory {
    private final Account account;
    private final List<AccountHistoryItem> accountHistoryItems;

    public AccountHistory(Account account, List<AccountHistoryItem> accountHistoryItems) {
        this.account = account;
        this.accountHistoryItems = accountHistoryItems;
    }

    public Account getAccount() {
        return account;
    }

    public List<AccountHistoryItem> getAccountHistoryItems() {
        return accountHistoryItems;
    }
}
