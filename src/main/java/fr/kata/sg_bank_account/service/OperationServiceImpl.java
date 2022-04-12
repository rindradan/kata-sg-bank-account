package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.OperationFailedException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;

import java.util.UUID;

public abstract class OperationServiceImpl implements OperationService {

    protected final UserService userService;
    protected final AccountService accountService;
    protected final AccountTransactionService accountTransactionService;

    protected OperationServiceImpl(UserService userService, AccountService accountService, AccountTransactionService accountTransactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountTransactionService = accountTransactionService;
    }

    abstract void action(User user, Account account, double amount);

    abstract void postAction(User user, Account account, double amount);

    public final void execute(UUID userId, double amount) throws OperationFailedException, UserNotFoundException, AccountNotFoundException {
        User user = userService.getUser(userId);
        Account account = accountService.getAccountByUserId(userId);
        validateExecution(account, amount);
        action(user, account, amount);
        postAction(user, account, amount);
    }
}
