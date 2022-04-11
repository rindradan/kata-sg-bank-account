package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.User;

public class OperationServiceImpl implements OperationService {

    private final AccountService accountService;

    public OperationServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void deposit(User user, double amount) throws AccountNotFoundException {
        var account = accountService.getAccountByUser(user.getId());
        account.setAmount(account.getAmount() + amount);
        accountService.saveAccount(account);
    }
}
