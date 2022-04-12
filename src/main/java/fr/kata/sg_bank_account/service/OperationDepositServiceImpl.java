package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.DepositNegativeAmountException;
import fr.kata.sg_bank_account.exception.OperationFailedException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;

import java.util.Date;

public class OperationDepositServiceImpl extends OperationServiceImpl implements OperationService {

    protected OperationDepositServiceImpl(UserService userService, AccountService accountService, AccountTransactionService accountTransactionService) {
        super(userService, accountService, accountTransactionService);
    }

    @Override
    public void validateExecution(Account account, double amount) throws OperationFailedException {
        if (amount < 0) {
            throw new DepositNegativeAmountException("Deposit failed because amount is negative!");
        }
    }

    @Override
    void action(User user, Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountService.saveAccount(account);
    }

    @Override
    void postAction(User user, Account account, double amount) {
        var accountTransaction = new AccountTransaction(new Date(), amount, TransactionType.DEPOSIT, account);
        accountTransactionService.createAccountTransaction(accountTransaction);
    }
}
