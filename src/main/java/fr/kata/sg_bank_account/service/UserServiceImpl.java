package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final Map<UUID,User> users = new HashMap<>();

    @Override
    public User getUser(UUID id) throws UserNotFoundException {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new UserNotFoundException("User with UUID: "+ id + " not found!");
    }

    @Override
    public Map<UUID, User> getUserMap() {
        return users;
    }
}
