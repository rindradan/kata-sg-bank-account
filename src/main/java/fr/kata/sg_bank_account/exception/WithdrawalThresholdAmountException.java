package fr.kata.sg_bank_account.exception;

public class WithdrawalThresholdAmountException extends Exception {

    public WithdrawalThresholdAmountException(String message) {
        super(message);
    }

    public WithdrawalThresholdAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
