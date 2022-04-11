package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.UserNotFoundException;
import fr.kata.sg_bank_account.model.User;

import java.util.Map;
import java.util.UUID;

public interface UserService {

    User getUser(UUID id) throws UserNotFoundException;

    Map<UUID,User> getUserMap();
}
