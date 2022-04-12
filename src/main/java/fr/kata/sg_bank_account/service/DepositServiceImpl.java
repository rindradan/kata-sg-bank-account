package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;

import java.util.UUID;

public class DepositServiceImpl implements DepositService {

    private final UserService userService;
    private final AccountService accountService;

    public DepositServiceImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public boolean canDeposit(UUID userId, double amount) {
        User user;
        try {
            user = userService.getUser(userId);
        } catch (UserNotFoundException e) {
            return false;
        }
        Account account;
        try {
            account = accountService.getAccountByUserId(userId);
        } catch (AccountNotFoundException e) {
            return false;
        }
        return user != null && account != null && amount >= 0;
    }
}
