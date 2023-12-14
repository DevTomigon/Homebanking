package com.mindhub.homebanking.Models;

import jakarta.persistence.*;


@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double maxAmount;

    private int clientLoanPayments;

    @ManyToOne
    private Loan loan;
    @ManyToOne
    private Client client;

    public ClientLoan() {
    }



    public ClientLoan(Double maxAmount , Integer clientLoanPayments) {
        this.maxAmount = maxAmount;
        this.clientLoanPayments = clientLoanPayments;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public Integer getClientLoanPayments() {
        return clientLoanPayments;
    }

    public Client getClient() {
        return client;
    }



    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setClientLoanPayments(Integer clientLoanPayments) {
        this.clientLoanPayments = clientLoanPayments;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }
}
