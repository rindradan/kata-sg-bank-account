package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.WithdrawalFailedException;
import fr.kata.sg_bank_account.exception.WithdrawalNegativeAmountException;
import fr.kata.sg_bank_account.exception.WithdrawalNotEnoughBalanceException;
import fr.kata.sg_bank_account.exception.WithdrawalThresholdAmountException;
import fr.kata.sg_bank_account.model.User;

@Deprecated(forRemoval = true)
public interface OperationService {

    void withdraw(User user, double amount) throws WithdrawalNotEnoughBalanceException, WithdrawalThresholdAmountException, WithdrawalNegativeAmountException, WithdrawalFailedException;
}
