package fr.kata.sg_bank_account.exception;

public class NotEnoughBalanceException extends Exception {

    public NotEnoughBalanceException(String message) {
        super(message);
    }

    public NotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
