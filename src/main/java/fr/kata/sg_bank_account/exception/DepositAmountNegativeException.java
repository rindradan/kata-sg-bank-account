package fr.kata.sg_bank_account.exception;

public class DepositAmountNegativeException extends Exception {

    public DepositAmountNegativeException(String message) {
        super(message);
    }

    public DepositAmountNegativeException(String message, Throwable cause) {
        super(message, cause);
    }
}
