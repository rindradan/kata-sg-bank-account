package fr.kata.sg_bank_account.exception;

public class AccountHistoryFailedException extends Exception {

    public AccountHistoryFailedException() {
    }

    public AccountHistoryFailedException(String message) {
        super(message);
    }

    public AccountHistoryFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
