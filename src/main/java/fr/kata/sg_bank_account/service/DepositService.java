package fr.kata.sg_bank_account.service;

import java.util.UUID;

public interface DepositService {

    boolean canDeposit(UUID userId, double amount);
}
