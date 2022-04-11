package fr.kata.sg_bank_account.exception;

public class DepositNegativeAmountException extends Exception {

    public DepositNegativeAmountException(String message) {
        super(message);
    }

    public DepositNegativeAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
