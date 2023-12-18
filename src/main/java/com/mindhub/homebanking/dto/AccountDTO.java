package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private final Long id;
    private final String number;
    private final LocalDate createDate;
    private final double balance;
    private final List<TransactionsDTO> transactions;

    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        createDate = account.getCreationDate();
        balance = account.getBalance();
        transactions = mapTransactions(account.getTransactions());
    }

    private List<TransactionsDTO> mapTransactions(List<Transactions> transactions) {
        return transactions.stream()
                .map(TransactionsDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getBalance() {
        return balance;
    }

    public List<TransactionsDTO> getTransactions() {
        return transactions;
    }
}
