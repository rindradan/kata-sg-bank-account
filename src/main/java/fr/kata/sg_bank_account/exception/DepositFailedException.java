package fr.kata.sg_bank_account.exception;

public class DepositFailedException extends OperationFailedException {

    public DepositFailedException() {
    }

    public DepositFailedException(String message) {
        super(message);
    }

    public DepositFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
