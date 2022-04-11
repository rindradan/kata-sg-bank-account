package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestOperationService {
    @Mock
    private AccountService accountService;
    @Spy
    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    void should_deposit_successfully() throws AccountNotFoundException {
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
}
