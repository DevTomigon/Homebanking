package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Enum.CardType;
import com.mindhub.homebanking.Enum.CardColor;

public class CardDTO {

    private final Long id;
    private final String cardNumber;
    private final int cvv;
    private final java.sql.Date thruDate;
    private final java.sql.Date fromDate;
    private final String cardHolder;
    private final CardType cardType;
    private final CardColor cardColor;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardNumber = card.getNumber();
        this.cvv = Integer.parseInt ( card.getCvv() );
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.cardHolder = card.getCardHolder();
        this.cardType = card.getType();
        this.cardColor = card.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public java.sql.Date getThruDate() {
        return thruDate;
    }

    public java.sql.Date getFromDate() {
        return fromDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardColor getCardColor() {
        return cardColor;
    }
}
