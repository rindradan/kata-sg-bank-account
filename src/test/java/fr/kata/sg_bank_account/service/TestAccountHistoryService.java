package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.TransactionType;
import fr.kata.sg_bank_account.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestAccountHistoryService {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private AccountServiceImpl accountService;
    @Mock
    private AccountTransactionServiceImpl accountTransactionService;
    @Spy
    @InjectMocks
    private AccountHistoryServiceImpl accountHistoryService;

    @Test
    void should_generate_account_history() throws ParseException, AccountNotFoundException, UserNotFoundException {
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var account = TestAccountHistoryServiceData.generateAccount();
        when(userService.getUser(userId)).thenReturn(account.getUser());
        when(accountService.getAccountByUserId(userId)).thenReturn(account);
        when(accountTransactionService.getAccountTransactionByUserId(userId)).thenReturn(TestAccountHistoryServiceData.generateAccountTransactions());

        var accountHistory = accountHistoryService.getAccountHistoryByUser(userId);
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        assertEquals(userId, accountHistory.getAccount().getUser().getId());
        assertEquals("John Doe", accountHistory.getAccount().getUser().getName());
        assertEquals(35, accountHistory.getAccount().getBalance());
        assertEquals(2, accountHistory.getAccountHistoryItems().size());

        assertEquals(UUID.fromString("844e5738-dcbb-4cff-960d-39fbe1101357"), accountHistory.getAccountHistoryItems().get(0).getId());
        assertEquals(formatter.parse("2022-01-15 07:10"), accountHistory.getAccountHistoryItems().get(0).getDate());
        assertEquals(50, accountHistory.getAccountHistoryItems().get(0).getAmount());
        assertEquals(TransactionType.DEPOSIT, accountHistory.getAccountHistoryItems().get(0).getTransactionType());

        assertEquals(UUID.fromString("369aea4e-5453-47a5-9557-f0850650ece7"), accountHistory.getAccountHistoryItems().get(1).getId());
        assertEquals(formatter.parse("2022-01-25 10:22"), accountHistory.getAccountHistoryItems().get(1).getDate());
        assertEquals(15, accountHistory.getAccountHistoryItems().get(1).getAmount());
        assertEquals(TransactionType.WITHDRAWAL, accountHistory.getAccountHistoryItems().get(1).getTransactionType());
    }

    @Test
    void should_generate_account_history_fail_when_user_not_exists() throws UserNotFoundException {
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        when(userService.getUser(userId)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> accountHistoryService.getAccountHistoryByUser(userId));
    }

    @Test
    void should_generate_account_history_fail_when_user_account_not_exists() throws UserNotFoundException, AccountNotFoundException {
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        when(userService.getUser(userId)).thenReturn(new User(userId, "John Doe"));
        when(accountService.getAccountByUserId(userId)).thenThrow(new AccountNotFoundException());

        assertThrows(AccountNotFoundException.class, () -> accountHistoryService.getAccountHistoryByUser(userId));
    }
}
