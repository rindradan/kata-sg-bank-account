package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.OperationFailedException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.Account;

import java.util.UUID;

public interface OperationServiceV2 {

    void validateExecution(Account account, double amount) throws OperationFailedException;

    void execute(UUID userId, double amount) throws OperationFailedException, UserNotFoundException, AccountNotFoundException;
}
