package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.OperationFailedException;

import java.util.UUID;

public interface OperationServiceV2 {

    void validateExecution(double amount) throws OperationFailedException;

    void execute(UUID userId, double amount) throws OperationFailedException;
}
