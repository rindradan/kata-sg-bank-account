package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.OperationFailedException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;

import java.util.UUID;

public abstract class OperationServiceImplV2 implements OperationServiceV2 {

    protected final UserService userService;
    protected final AccountService accountService;
    protected final AccountTransactionService accountTransactionService;

    protected OperationServiceImplV2(UserService userService, AccountService accountService, AccountTransactionService accountTransactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    abstract OperationFailedException getOperationException();

    final User prepareUser(UUID userId) throws OperationFailedException {
        try {
            return userService.getUser(userId);
        } catch (UserNotFoundException e) {
            throw getOperationException();
        }
    }

    final Account prepareAccount(UUID userId) throws OperationFailedException {
        try {
            return accountService.getAccountByUserId(userId);
        } catch (AccountNotFoundException e) {
            throw getOperationException();
        }
    }

    abstract void action(User user, Account account, double amount);

    abstract void postAction(User user, Account account, double amount);

    public final void execute(UUID userId, double amount) throws OperationFailedException {
        User user = prepareUser(userId);
        Account account = prepareAccount(userId);
        validateExecution(amount);
        action(user, account, amount);
        postAction(user, account, amount);
    }
}
