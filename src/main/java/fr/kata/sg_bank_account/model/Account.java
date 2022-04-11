package fr.kata.sg_bank_account.model;

import java.util.UUID;

public class Account {
    private final UUID id;
    private final User user;
    private double balance;

    public Account(UUID id, User user, double balance) {
        this.id = id;
        this.user = user;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
