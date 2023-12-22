package com.mindhub.homebanking.dto;

import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Models.Client;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    private final String password;
    private final List<AccountDTO> accounts;
    private final List<ClientLoanDTO> loans;
    private final List<CardDTO> cards;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword ();

        this.accounts = client.getAccounts().stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());

        this.loans = client.getClientLoans().stream()
                .map(ClientLoanDTO::new)
                .collect(Collectors.toList());

        this.cards = client.getCards().stream()
                .map(CardDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public List<ClientLoanDTO> getLoans() {
        return loans;
    }

    public String getPassword() {return password;}

    public List<CardDTO> getCards() {
        return cards;
    }
}
