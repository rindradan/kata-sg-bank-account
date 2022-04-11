package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.*;
import fr.kata.sg_bank_account.model.User;

public class OperationServiceImpl implements OperationService {

    private static final double WITHDRAWAL_AMOUNT_THRESHOLD = 10;
    private final AccountService accountService;

    public OperationServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void deposit(User user, double amount) throws DepositFailedException, DepositAmountNegativeException {
        if (amount < 0) {
            throw new DepositAmountNegativeException("Deposit failed because amount is negative!");
        }
        try {
            var account = accountService.getAccountByUser(user.getId());
            account.setAmount(account.getAmount() + amount);
            accountService.saveAccount(account);
        } catch (AccountNotFoundException e) {
            throw new DepositFailedException("Deposit failed because User: "+ user.getId() +" has no account!", e);
        }
    }

    @Override
    public void withdraw(User user, double amount) throws AccountNotFoundException, NotEnoughBalanceException, WithdrawalThresholdException {
        if (amount <= WITHDRAWAL_AMOUNT_THRESHOLD) {
            throw new WithdrawalThresholdException("Withdrawal failed because amount threshold not reached: "+ amount);
        }
        var account = accountService.getAccountByUser(user.getId());
        if (account.getAmount() >= amount) {
            account.setAmount(account.getAmount() - amount);
            accountService.saveAccount(account);
        } else {
            throw new NotEnoughBalanceException("Withdrawal failed because not enough balance for user: " + user.getId());
        }
    }
}
