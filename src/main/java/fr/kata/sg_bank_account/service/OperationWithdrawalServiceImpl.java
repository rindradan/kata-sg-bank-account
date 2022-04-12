package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.OperationFailedException;
import fr.kata.sg_bank_account.exception.WithdrawalNegativeAmountException;
import fr.kata.sg_bank_account.exception.WithdrawalNotEnoughBalanceException;
import fr.kata.sg_bank_account.exception.WithdrawalThresholdAmountException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;

import java.util.Date;

public class OperationWithdrawalServiceImpl extends OperationServiceImplV2 implements OperationServiceV2 {

    private static final double WITHDRAWAL_THRESHOLD_AMOUNT = 10;
    private static final double WITHDRAWAL_NEGATIVE_AMOUNT = 0;

    protected OperationWithdrawalServiceImpl(UserService userService, AccountService accountService, AccountTransactionService accountTransactionService) {
        super(userService, accountService, accountTransactionService);
    }

    @Override
    public void validateExecution(Account account, double amount) throws OperationFailedException {
        if (amount <= WITHDRAWAL_NEGATIVE_AMOUNT) {
            throw new WithdrawalNegativeAmountException("Withdrawal failed because amount is negative");
        }
        if (amount <= WITHDRAWAL_THRESHOLD_AMOUNT) {
            throw new WithdrawalThresholdAmountException("Withdrawal failed because amount threshold not reached: "+ amount);
        }
        if (account.getBalance() < amount) {
            throw new WithdrawalNotEnoughBalanceException("Withdrawal failed because not enough balance for user: " + account.getUser().getId());
        }
    }

    @Override
    void action(User user, Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        accountService.saveAccount(account);
    }

    @Override
    void postAction(User user, Account account, double amount) {
        var accountTransaction = new AccountTransaction(new Date(), amount, TransactionType.WITHDRAWAL, account);
        accountTransactionService.createAccountTransaction(accountTransaction);
    }
}
