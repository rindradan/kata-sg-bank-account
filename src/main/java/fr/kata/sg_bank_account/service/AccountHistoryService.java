package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.dto.AccountHistory;
import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;

import java.util.UUID;

public interface AccountHistoryService {

    AccountHistory getAccountHistoryByUser(UUID userId) throws AccountNotFoundException, UserNotFoundException;
}
