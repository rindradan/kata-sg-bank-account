package fr.kata.sg_bank_account.exception;

public class WithdrawalNotEnoughBalanceException extends OperationFailedException {

    public WithdrawalNotEnoughBalanceException() {
    }

    public WithdrawalNotEnoughBalanceException(String message) {
        super(message);
    }

    public WithdrawalNotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
