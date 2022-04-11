package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final Map<UUID,Account> accountMap = new HashMap<>();

    @Override
    public Account getAccountByUser(UUID userId) {
        return accountMap.get(userId);
    }

    @Override
    public Map<UUID, Account> getAccountMap() {
        return accountMap;
    }
}
