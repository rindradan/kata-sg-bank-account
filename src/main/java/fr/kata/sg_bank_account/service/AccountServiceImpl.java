package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final Map<UUID,Account> accountMap = new HashMap<>();

    @Override
    public Map<UUID, Account> getAccountMap() {
        return accountMap;
    }

    @Override
    public Account  getAccountByUser(UUID userId) throws AccountNotFoundException {
        if (accountMap.containsKey(userId)) {
            return accountMap.get(userId);
        }
        throw new AccountNotFoundException("Account of User with UUID: "+ userId +" not found");
    }

    @Override
    public Account saveAccount(Account account) {
        accountMap.put(account.getUser().getId(), account);
        return accountMap.get(account.getUser().getId());
    }
}
