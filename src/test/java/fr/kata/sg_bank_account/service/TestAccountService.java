package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAccountService {

    @Test
    void should_get_account_by_user() throws AccountNotFoundException {
        AccountService accountService = new AccountServiceImpl();
        accountService.getAccountMap().putAll(TestAccountServiceData.generateAccounts());

        UUID userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        Account account = accountService.getAccountByUser(userId);
        assertEquals(UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002"), account.getId());
        assertEquals(userId, account.getUser().getId());
        assertEquals(10, account.getAmount());
    }

    @Test
    void should_fail_when_account_not_found() {
        assertThrows(AccountNotFoundException.class, () -> {
            AccountService accountService = new AccountServiceImpl();
            accountService.getAccountByUser(UUID.randomUUID());
        });
    }
}
