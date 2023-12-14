package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.Models.ClientLoan;
import com.mindhub.homebanking.Models.Loan;

import java.util.List;

public class ClientLoanDTO {

    private final Long id;
    private final String name;
    private final Double maxAmount;
    private final int payments;

    private final Long loanId;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId ();
        this.name = clientLoan.getLoan ().getName ();
        this.maxAmount = clientLoan.getMaxAmount ();
        this.payments = clientLoan.getClientLoanPayments ();
        this.loanId = clientLoan.getLoan ().getId ();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public int getPayments() {
        return payments;
    }

    public Long getLoanId() {
        return loanId;
    }
}
