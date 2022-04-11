package fr.kata.sg_bank_account.model;

import java.util.UUID;

public class Account {
    private final UUID id;
    private final User user;
    private double amount;

    public Account(UUID id, User user, double amount) {
        this.id = id;
        this.user = user;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
