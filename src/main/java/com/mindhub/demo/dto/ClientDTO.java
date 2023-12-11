package com.mindhub.demo.dto;

import com.mindhub.demo.Models.Client;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<AccountDTO> accounts;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();


        this.accounts = client.getAccounts().stream()
                .map(AccountDTO::new)
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
}
