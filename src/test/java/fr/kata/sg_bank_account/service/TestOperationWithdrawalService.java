package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.*;
import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.AccountTransaction;
import fr.kata.sg_bank_account.model.TransactionType;
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
class TestOperationWithdrawalService {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private AccountServiceImpl accountService;
    @Mock
    private AccountTransactionServiceImpl accountTransactionService;
    @Spy
    @InjectMocks
    private OperationWithdrawalServiceImpl operationWithdrawalService;

    @Test
    void should_withdraw_successfully() throws AccountNotFoundException, OperationFailedException, UserNotFoundException {
        var userId = UUID.randomUUID();
        var initialBalance = 40;
        var withdrawalAmount = 25;
        var actualBalance = 15;
        var user = new User(userId, "John Doe");
        when(userService.getUser(any(UUID.class))).thenReturn(user);
        var account = new Account(UUID.randomUUID(), user, initialBalance);
        when(accountService.getAccountByUserId(userId)).thenReturn(account);

        operationWithdrawalService.execute(userId, withdrawalAmount);

        verify(userService, times(1)).getUser(userId);
        verify(accountService, times(1)).getAccountByUserId(userId);
        verify(operationWithdrawalService, times(1)).validateExecution(account, withdrawalAmount);

        var accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountService, times(1)).saveAccount(accountCaptor.capture());
        assertEquals(actualBalance, accountCaptor.getValue().getBalance());

        var transactionCaptor = ArgumentCaptor.forClass(AccountTransaction.class);
        verify(accountTransactionService, times(1)).createAccountTransaction(transactionCaptor.capture());
        assertEquals(withdrawalAmount, transactionCaptor.getValue().getAmount());
        assertEquals(TransactionType.WITHDRAWAL, transactionCaptor.getValue().getTransactionType());
        assertEquals(account, transactionCaptor.getValue().getAccount());
    }

    @Test
    void should_withdrawal_fail_when_not_enough_balance() throws AccountNotFoundException {
        var userId = UUID.randomUUID();
        var user = new User(userId, "John Doe");
        var account = new Account(UUID.randomUUID(), user, 7);
        when(accountService.getAccountByUserId(userId)).thenReturn(account);

        assertThrows(WithdrawalNotEnoughBalanceException.class, () -> operationWithdrawalService.execute(userId, 20));
    }

    @Test
    void should_withdrawal_fail_when_amount_less_than_threshold() {
        var userId = UUID.randomUUID();
        assertThrows(WithdrawalThresholdAmountException.class, () -> operationWithdrawalService.execute(userId, 10));
    }

    @Test
    void should_withdrawal_fail_when_negative_amount() {
        var userId = UUID.randomUUID();
        assertThrows(WithdrawalNegativeAmountException.class, () -> operationWithdrawalService.execute(userId, -50));
    }

    @Test
    void should_withdrawal_fail_when_user_has_no_account() throws AccountNotFoundException {
        var userId = UUID.randomUUID();
        when(accountService.getAccountByUserId(any(UUID.class))).thenThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> operationWithdrawalService.execute(userId, 20));
    }
}
