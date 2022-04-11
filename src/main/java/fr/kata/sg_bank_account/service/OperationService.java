package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.User;

public interface OperationService {
    void deposit(User user, double amount) throws AccountNotFoundException;
}
