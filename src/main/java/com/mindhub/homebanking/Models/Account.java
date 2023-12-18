package com.mindhub.homebanking.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Transactions> transactions = new ArrayList<> ();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();

    public Account(String number, LocalDate creationDate, Double balance) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transactions transactions) {
        transactions.setAccount(this);
        this.transactions.add(transactions);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        card.setAccount(this);
        card.setClient(this.client);
    }
}
