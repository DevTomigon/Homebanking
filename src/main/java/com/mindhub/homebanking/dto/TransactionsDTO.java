package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.Enum.TransactionType;
import com.mindhub.homebanking.Models.Transaction;

import java.time.LocalDateTime;

public class TransactionsDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private LocalDateTime date;
    private String description;

    public TransactionsDTO(Transaction transactions){
        id = transactions.getId();
        date = transactions.getDate();
        amount = transactions.getAmount();
        type = transactions.getType();
        description = transactions.getDescription();
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
