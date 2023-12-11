package com.mindhub.demo.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Client {   



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName, lastName, email;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();


    public Client() {
    }

    public Client(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getFirstName () {
            return firstName;
        }



        public String getLastName () {
            return lastName;
        }



        public String getEmail () {
            return email;
        }






    public void addAccount(Account account) {
        accounts.add(account);
        account.setClient(this);
    }

    @Override
        public String toString () {
            return "Cliente{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", id=" + id +
                    '}';
        }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Long getId() {
        return id;
    }
}


