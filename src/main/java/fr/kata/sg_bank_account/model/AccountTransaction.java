package fr.kata.sg_bank_account.model;

import java.util.Date;
import java.util.UUID;

public class AccountTransaction {
    private UUID id;
    private final Date date;
    private final double amount;
    private final TransactionType transactionType;
    private final Account account;

    public AccountTransaction(Date date, double amount, TransactionType transactionType, Account account) {
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
    }

    public AccountTransaction(UUID id, Date date, double amount, TransactionType transactionType, Account account) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Account getAccount() {
        return account;
    }
}
