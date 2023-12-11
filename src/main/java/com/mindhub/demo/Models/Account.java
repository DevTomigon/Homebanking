package com.mindhub.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Account() {

    }

    public Account(String number, LocalDate creationDate, Double balance) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }




    public Long getId() {
        return id;
    }


    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {return balance; }

    public void setClient(Client client) {
        this.client = client;
    }
}
