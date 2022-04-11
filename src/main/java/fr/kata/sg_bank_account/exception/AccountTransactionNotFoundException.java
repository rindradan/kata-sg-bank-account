package fr.kata.sg_bank_account.exception;

public class AccountTransactionNotFoundException extends Exception {

    public AccountTransactionNotFoundException() {
    }

    public AccountTransactionNotFoundException(String message) {
        super(message);
    }

    public AccountTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
