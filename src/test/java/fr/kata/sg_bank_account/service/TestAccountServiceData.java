package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.Account;
import fr.kata.sg_bank_account.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestAccountServiceData {

    static Map<UUID,Account> generateAccounts() {
        var accountMap = new HashMap<UUID,Account>();
        var accountId = UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002");
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var user = new User(userId, "John Doe");
        accountMap.put(userId, new Account(accountId, user, 10));

        var testAccountId = UUID.randomUUID();
        var testUserId = UUID.randomUUID();
        var testUser = new User(userId, "Test User");
        accountMap.put(testUserId, new Account(testAccountId, testUser, 200));

        return accountMap;
    }

    static Account generateAccount() {
        var accountId = UUID.fromString("90cc9ce0-b9a0-11ec-8422-0242ac120002");
        var userId = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var user = new User(userId, "John Doe");
        return new Account(accountId, user, 10);
    }
}
