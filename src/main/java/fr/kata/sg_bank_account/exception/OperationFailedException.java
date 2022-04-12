package fr.kata.sg_bank_account.exception;

public class OperationFailedException extends Exception {

    public OperationFailedException() {
    }

    public OperationFailedException(String message) {
        super(message);
    }

    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
