package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountNotFoundException;
import fr.kata.sg_bank_account.model.Account;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TestAccountService {

    @Test
    void should_get_account_by_user() throws AccountNotFoundException {
        var accountService = new AccountServiceImpl(TestAccountServiceData.generateAccounts());

        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var account = accountService.getAccountByUserId(userId);
        assertEquals(UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002"), account.getId());
        assertEquals(userId, account.getUser().getId());
        assertEquals(10, account.getBalance());
    }

    @Test
    void should_fail_when_account_not_found() {
        assertThrows(AccountNotFoundException.class, () -> new AccountServiceImpl(Collections.emptyMap()).getAccountByUserId(UUID.randomUUID()));
    }

    @Test
    void should_update_existing_account() {
        var accountService = new AccountServiceImpl(TestAccountServiceData.generateAccounts());

        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var account = TestAccountServiceData.generateAccount();
        assertEquals(UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002"), account.getId());
        assertEquals(userId, account.getUser().getId());
        assertEquals(10, account.getBalance());

        account.setBalance(50);
        var savedAccount = accountService.saveAccount(account);

        assertEquals(50, savedAccount.getBalance());
    }

    @Test
    void should_create_new_account() {
        var initialAccountMap = new HashMap<UUID,Account>();
        var accountService = new AccountServiceImpl(initialAccountMap);

        var account = TestAccountServiceData.generateAccount();
        accountService.saveAccount(account);

        assertFalse(initialAccountMap.isEmpty());
    }
}
