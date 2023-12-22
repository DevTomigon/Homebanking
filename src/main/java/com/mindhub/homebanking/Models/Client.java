package com.mindhub.homebanking.Models;
import java.util.HashSet;

import com.mindhub.homebanking.Enum.RoleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName, lastName, email, password;

    private boolean existByEmail;

    private RoleType role = RoleType.CLIENT;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Agregada CascadeType.ALL
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName , String lastName , String email , String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Métodos getters y setters

    public void addAccount(Account account) {
        accounts.add(account);
        account.setClient(this);
    }

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setClient(this);
        this.clientLoans.add(clientLoan);
    }

    public void addCard(Card card) {
        this.cards.add(card);
        card.setClient(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }



    public boolean isExistByEmail() {
        return existByEmail;
    }

    public void setExistByEmail(boolean existByEmail) {
        this.existByEmail = existByEmail;
    }



    public List<Card> getCards() {
        return cards;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Set<ClientLoan> getLoans() {
        return clientLoans;
    }
}
