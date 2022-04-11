package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.DepositAmountNegativeException;
import fr.kata.sg_bank_account.exception.DepositFailedException;
import fr.kata.sg_bank_account.exception.NotEnoughBalanceException;
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
    void should_deposit_successfully() throws AccountNotFoundException, DepositFailedException, DepositAmountNegativeException {
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
        assertThrows(DepositAmountNegativeException.class, () -> operationService.deposit(new User(UUID.randomUUID(), "John Doe"), -10));
    }

    @Test
    void should_deposit_fail_when_user_has_no_account() throws AccountNotFoundException {
        when(accountService.getAccountByUser(any(UUID.class))).thenThrow(AccountNotFoundException.class);
        assertThrows(DepositFailedException.class, () -> operationService.deposit(new User(UUID.randomUUID(), "John Doe"), 10));
    }

    @Test
    void should_withdraw_successfully() throws AccountNotFoundException, NotEnoughBalanceException {
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
    void should_withdraw_fail_when_not_enough_balance() throws AccountNotFoundException {
        var userId = UUID.randomUUID();
        User user = new User(userId, "John Doe");
        var account = new Account(UUID.randomUUID(), user, 7);
        when(accountService.getAccountByUser(userId)).thenReturn(account);

        assertThrows(NotEnoughBalanceException.class, () -> operationService.withdraw(user, 20));
    }
}
