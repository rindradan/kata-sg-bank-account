package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.*;
import fr.kata.sg_bank_account.model.User;

@Deprecated(forRemoval = true)
public interface OperationService {

    void deposit(User user, double amount) throws DepositFailedException, DepositNegativeAmountException;

    void withdraw(User user, double amount) throws WithdrawalNotEnoughBalanceException, WithdrawalThresholdAmountException, WithdrawalNegativeAmountException, WithdrawalFailedException;
}
