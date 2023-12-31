    package com.mindhub.homebanking.Models;

    import jakarta.persistence.*;

    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    @Entity
    public class Loan {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private Double maxAmount;


        @ElementCollection
        private List<Integer> payments;

        @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
        private Set<ClientLoan> clientLoans = new HashSet<> ();


        public Loan(String name , Double maxAmount , List<Integer> payments) {
            this.name = name;
            this.maxAmount = maxAmount;
            this.payments = payments;
        }

        public Loan() {

        }

        public Long getId() {
            return id;
        }

        public Set<ClientLoan> getClientLoans() {
            return clientLoans;
        }



        public String getName() {
            return name;
        }

        public Double getMaxAmount() {
            return maxAmount;
        }

        public List<Integer> getPayments() {
            return payments;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMaxAmount(Double maxAmount) {
            this.maxAmount = maxAmount;
        }

        public void setPayments(List<Integer> payments) {
            this.payments = payments;
        }

        public void setClientLoans(Set<ClientLoan> clientLoans) {
            this.clientLoans = clientLoans;
        }

        public void addClientLoan(ClientLoan clientLoan) {
            clientLoan.setLoan(this);
            this.clientLoans.add(clientLoan);
        }

        @Override
        public String toString() {
            return "Loan{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", maxAmount=" + maxAmount +
                    ", payments=" + payments +
                    '}';
        }



    }
