package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.Account;

import java.util.Map;
import java.util.UUID;

public interface AccountService {
    Account getAccountByUser(UUID userId) throws AccountNotFoundException;
    Map<UUID,Account> getAccountMap();
}
