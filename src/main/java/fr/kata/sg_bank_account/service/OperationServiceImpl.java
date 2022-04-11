package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.DepositFailedException;
import fr.kata.sg_bank_account.model.User;

public class OperationServiceImpl implements OperationService {

    private final AccountService accountService;

    public OperationServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void deposit(User user, double amount) throws DepositFailedException {
        if (amount < 0) {
            throw new DepositFailedException("Deposit amount is not valid!");
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
    public void withdraw(User user, double amount) throws AccountNotFoundException {
        var account = accountService.getAccountByUser(user.getId());
        account.setAmount(account.getAmount() - amount);
        accountService.saveAccount(account);
    }
}
