package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountHistoryFailedException;
import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.TransactionType;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestAccountHistoryService {

    @Mock
    private AccountServiceImpl accountService;
    @Mock
    private AccountTransactionServiceImpl accountTransactionService;
    @Spy
    @InjectMocks
    private AccountHistoryServiceImpl accountHistoryService;

    @Test
    void should_generate_account_history() throws ParseException, AccountHistoryFailedException, AccountNotFoundException {
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        when(accountService.getAccountByUserId(userId)).thenReturn(TestAccountHistoryServiceData.generateAccount());
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
}
