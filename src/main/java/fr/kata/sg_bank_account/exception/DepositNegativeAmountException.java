package fr.kata.sg_bank_account.exception;

public class DepositNegativeAmountException extends OperationFailedException {

    public DepositNegativeAmountException() {
    }

    public DepositNegativeAmountException(String message) {
        super(message);
    }

    public DepositNegativeAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
