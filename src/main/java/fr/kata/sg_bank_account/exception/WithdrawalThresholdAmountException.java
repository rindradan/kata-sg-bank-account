package fr.kata.sg_bank_account.exception;

public class WithdrawalThresholdAmountException extends OperationFailedException {

    public WithdrawalThresholdAmountException() {
    }

    public WithdrawalThresholdAmountException(String message) {
        super(message);
    }

    public WithdrawalThresholdAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
