package fr.kata.sg_bank_account.exception;

public class WithdrawalNegativeAmountException extends OperationFailedException {

    public WithdrawalNegativeAmountException() {
    }

    public WithdrawalNegativeAmountException(String message) {
        super(message);
    }

    public WithdrawalNegativeAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
