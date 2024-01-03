package com.mindhub.homebanking.Models;

import com.mindhub.homebanking.Enum.CardColor;
import com.mindhub.homebanking.Enum.CardType;
import jakarta.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // No necesitas esta relación a menos que también quieras asociar la tarjeta a una cuenta específica.
    // Si no, simplemente omítelo.
    // @ManyToOne
    // @JoinColumn(name = "account_id")
    // private Account account;

    private String number;
    private String cvv;
    private java.sql.Date thruDate;
    private java.sql.Date fromDate;
    private String cardHolder;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @Enumerated(EnumType.STRING)
    private CardColor color;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Card() {
    }

    public Card(CardColor color, CardType type, String number, String cardHolder, int cvv, java.sql.Date fromDate, java.sql.Date thruDate) {
        this.color = color;
        this.type = type;
        this.number = number;
        this.cardHolder = cardHolder;
        this.cvv = String.valueOf ( cvv );
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    // Otros getters y setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public java.sql.Date getThruDate() {
        return thruDate;
    }

    public void setThruDate(java.sql.Date thruDate) {
        this.thruDate = thruDate;
    }

    public java.sql.Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(java.sql.Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
