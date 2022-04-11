package fr.kata.sg_bank_account.service;

import fr.kata.sg_bank_account.exception.AccountTransactionNotFoundException;
import fr.kata.sg_bank_account.model.AccountTransaction;

import java.util.List;
import java.util.UUID;

public interface AccountTransactionService {

    List<AccountTransaction> getAccountTransactions();

    AccountTransaction createAccountTransaction(AccountTransaction accountTransaction);

    AccountTransaction getAccountTransactionById(UUID id) throws AccountTransactionNotFoundException;
}
