package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.*;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;

import java.util.Date;

public class OperationServiceImpl implements OperationService {

    private static final double WITHDRAWAL_THRESHOLD_AMOUNT = 10;
    private static final double WITHDRAWAL_NEGATIVE_AMOUNT = 0;
    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;

    public OperationServiceImpl(AccountService accountService, AccountTransactionService accountTransactionService) {
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    @Override
    public void deposit(User user, double amount) throws DepositFailedException, DepositNegativeAmountException {
        if (amount < 0) {
            throw new DepositNegativeAmountException("Deposit failed because amount is negative!");
        }
        try {
            var account = accountService.getAccountByUserId(user.getId());
            account.setBalance(account.getBalance() + amount);
            accountService.saveAccount(account);

            var accountTransaction = new AccountTransaction(new Date(), amount, TransactionType.DEPOSIT, account);
            accountTransactionService.createAccountTransaction(accountTransaction);
        } catch (AccountNotFoundException e) {
            throw new DepositFailedException("Deposit failed because User: "+ user.getId() +" has no account!", e);
        }
    }

    @Override
    public void withdraw(User user, double amount) throws WithdrawalNotEnoughBalanceException, WithdrawalThresholdAmountException, WithdrawalNegativeAmountException, WithdrawalFailedException {
        if (amount <= WITHDRAWAL_NEGATIVE_AMOUNT) {
            throw new WithdrawalNegativeAmountException("Withdrawal failed because amount is negative");
        }
        if (amount <= WITHDRAWAL_THRESHOLD_AMOUNT) {
            throw new WithdrawalThresholdAmountException("Withdrawal failed because amount threshold not reached: "+ amount);
        }
        try {
            var account = accountService.getAccountByUserId(user.getId());
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountService.saveAccount(account);

                var accountTransaction = new AccountTransaction(new Date(), amount, TransactionType.WITHDRAWAL, account);
                accountTransactionService.createAccountTransaction(accountTransaction);
            } else {
                throw new WithdrawalNotEnoughBalanceException("Withdrawal failed because not enough balance for user: " + user.getId());
            }
        } catch (AccountNotFoundException e) {
            throw new WithdrawalFailedException("Withdrawal failed because user: "+ user.getId() +" has no account");
        }
    }
}
