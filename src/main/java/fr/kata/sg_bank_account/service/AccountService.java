package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.Account;

import java.util.UUID;

public interface AccountService {

    Account getAccountByUserId(UUID userId) throws AccountNotFoundException;

    Account saveAccount(Account account);
}
