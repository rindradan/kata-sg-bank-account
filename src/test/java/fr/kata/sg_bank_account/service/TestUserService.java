package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestUserService {

    @Test
    void should_get_single_user() throws UserNotFoundException {
        var userService = new UserServiceImpl(TestUserServiceData.generateUsers());

        var uuid = UUID.fromString("dd8d795c-b980-11ec-8422-0242ac120002");
        var user = userService.getUser(uuid);
        assertEquals(uuid, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void should_fail_when_user_not_found() {
        assertThrows(UserNotFoundException.class, () -> new UserServiceImpl(Collections.emptyMap()).getUser(UUID.randomUUID()));
    }
}
