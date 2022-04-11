package fr.kata.sg_bank_account.dto;

import fr.kata.sg_bank_account.model.TransactionType;

import java.util.Date;
import java.util.UUID;

public class AccountHistoryItem {
    private final UUID id;
    private final Date date;
    private final double amount;
    private final TransactionType transactionType;

    public AccountHistoryItem(UUID id, Date date, double amount, TransactionType transactionType) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
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
}
