package fr.kata.sg_bank_account.exception;

public class WithdrawalNegativeAmountException extends Exception {

    public WithdrawalNegativeAmountException(String message) {
        super(message);
    }

    public WithdrawalNegativeAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
