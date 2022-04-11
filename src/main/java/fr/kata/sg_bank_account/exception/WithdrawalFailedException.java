package fr.kata.sg_bank_account.exception;

public class WithdrawalFailedException extends Exception {

    public WithdrawalFailedException(String message) {
        super(message);
    }

    public WithdrawalFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
