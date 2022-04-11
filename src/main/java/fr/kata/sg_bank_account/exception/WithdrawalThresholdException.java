package fr.kata.sg_bank_account.exception;

public class WithdrawalThresholdException extends Exception {

    public WithdrawalThresholdException(String message) {
        super(message);
    }

    public WithdrawalThresholdException(String message, Throwable cause) {
        super(message, cause);
    }
}
