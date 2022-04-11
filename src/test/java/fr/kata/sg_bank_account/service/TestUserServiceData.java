package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestUserServiceData {

    static Map<UUID, User> generateUsers() {
        var users = new HashMap<UUID,User>();
        var id = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        users.put(id, new User(id, "John Doe"));

        var randomId = UUID.randomUUID();
        users.put(randomId, new User(randomId, "Random User"));

        var testId = UUID.randomUUID();
        users.put(testId, new User(testId, "Test User"));
        return users;
    }
}
