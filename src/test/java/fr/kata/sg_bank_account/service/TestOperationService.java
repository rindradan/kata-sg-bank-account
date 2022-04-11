package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.*;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestOperationService {
    @Mock
    private AccountServiceImpl accountService;
    @Spy
    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    void should_deposit_successfully() throws AccountNotFoundException, DepositFailedException, DepositNegativeAmountException {
        var userId = UUID.randomUUID();
        var user = new User(userId, "John Doe");
        var account = new Account(UUID.randomUUID(), user, 10);
        when(accountService.getAccountByUser(userId)).thenReturn(account);

        operationService.deposit(user, 40);

        verify(accountService, times(1)).getAccountByUser(user.getId());
        var accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountService, times(1)).saveAccount(accountCaptor.capture());
        assertEquals(50, accountCaptor.getValue().getAmount());
    }

    @Test
    void should_deposit_fail_when_negative_amount() {
        assertThrows(DepositNegativeAmountException.class, () -> operationService.deposit(new User(UUID.randomUUID(), "John Doe"), -10));
    }

    @Test
    void should_deposit_fail_when_user_has_no_account() throws AccountNotFoundException {
        when(accountService.getAccountByUser(any(UUID.class))).thenThrow(AccountNotFoundException.class);
        assertThrows(DepositFailedException.class, () -> operationService.deposit(new User(UUID.randomUUID(), "John Doe"), 10));
    }

    @Test
    void should_withdraw_successfully() throws AccountNotFoundException, WithdrawalNotEnoughBalanceException, WithdrawalThresholdAmountException, WithdrawalNegativeAmountException, WithdrawalFailedException {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        var account = new Account(UUID.randomUUID(), user, 40);
        when(accountService.getAccountByUser(userId)).thenReturn(account);

        operationService.withdraw(user, 25);

        verify(accountService, times(1)).getAccountByUser(user.getId());
        var accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountService, times(1)).saveAccount(accountCaptor.capture());
        assertEquals(15, accountCaptor.getValue().getAmount());
    }

    @Test
    void should_withdrawal_fail_when_not_enough_balance() throws AccountNotFoundException {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        var account = new Account(UUID.randomUUID(), user, 7);
        when(accountService.getAccountByUser(userId)).thenReturn(account);

        assertThrows(WithdrawalNotEnoughBalanceException.class, () -> operationService.withdraw(user, 20));
    }

    @Test
    void should_withdrawal_fail_when_amount_less_than_threshold() {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        assertThrows(WithdrawalThresholdAmountException.class, () -> operationService.withdraw(user, 10));
    }

    @Test
    void should_withdrawal_fail_when_negative_amount() {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        assertThrows(WithdrawalNegativeAmountException.class, () -> operationService.withdraw(user, -50));
    }

    @Test
    void should_withdrawal_fail_when_user_has_no_account() throws AccountNotFoundException {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        when(accountService.getAccountByUser(any(UUID.class))).thenThrow(AccountNotFoundException.class);
        assertThrows(WithdrawalFailedException.class, () -> operationService.withdraw(user, 20));
    }
}
