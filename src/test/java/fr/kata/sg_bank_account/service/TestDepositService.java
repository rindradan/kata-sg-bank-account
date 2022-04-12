package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestDepositService {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private AccountServiceImpl accountService;
    @Spy
    @InjectMocks
    private DepositServiceImpl depositService;

    @Test
    void should_be_able_to_deposit() throws UserNotFoundException, AccountNotFoundException {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        Account account = new Account(UUID.randomUUID(), user);
        when(accountService.getAccountByUserId(any(UUID.class))).thenReturn(account);
        assertTrue(depositService.canDeposit(userId, 20));
    }

    @Test
    void should_not_be_able_to_deposit_when_user_not_exists() throws UserNotFoundException {
        UUID userId = UUID.randomUUID();
        when(userService.getUser(any(UUID.class))).thenThrow(UserNotFoundException.class);
        assertFalse(depositService.canDeposit(userId, 20));
    }

    @Test
    void should_not_be_able_to_deposit_when_user_account_not_exists() throws UserNotFoundException, AccountNotFoundException {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        when(accountService.getAccountByUserId(any(UUID.class))).thenThrow(AccountNotFoundException.class);
        assertFalse(depositService.canDeposit(userId, 20));
    }

    @Test
    void should_not_be_able_to_deposit_when_amount_negative() throws UserNotFoundException, AccountNotFoundException {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        Account account = new Account(UUID.randomUUID(), user);
        when(accountService.getAccountByUserId(any(UUID.class))).thenReturn(account);
        assertFalse(depositService.canDeposit(userId, -5));
    }
}
