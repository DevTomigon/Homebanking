package com.mindhub.homebanking;

import com.mindhub.homebanking.Enum.CardColor;
import com.mindhub.homebanking.Enum.CardType;
import com.mindhub.homebanking.Enum.RoleType;
import com.mindhub.homebanking.Enum.TransactionType;
import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(
			ClientRepository clientRepository,
			AccountRepository accountRepository,
			TransactionRepository transactionRepository,
			LoanRepository loanRepository,
			ClientLoanRepository clientLoanRepository,
			CardRepository cardRepository) {

		return args -> {
			// Crear clientes
			Client tomas = new Client("Tomas", "Gonzalez", "tomigonzalez898@gmail.com", passwordEncoder.encode("12345"));
			Client maribel = new Client("Maribel", "Perez", "mariperez322@gmail.com", passwordEncoder.encode("12345"));

			clientRepository.save(tomas);
			clientRepository.save(maribel);
			System.out.println("Clientes guardados");
			tomas.setRole(RoleType.ADMIN);

			// Crear cuentas para Tomas
			Account cuenta1Tomas = new Account("VIN001", LocalDate.now(), 5000.0);
			Account cuenta2Tomas = new Account("VIN002", LocalDate.now().plusDays(1), 7500.0);

			accountRepository.save(cuenta1Tomas);
			accountRepository.save(cuenta2Tomas);
			System.out.println("Cuentas de Tomas guardadas");

			// Crear cuentas para Maribel
			Account cuenta1Maribel = new Account("VIN003", LocalDate.now(), 10000.0);
			Account cuenta2Maribel = new Account("VIN004", LocalDate.now().plusDays(1), 15000.0);

			accountRepository.save(cuenta1Maribel);
			accountRepository.save(cuenta2Maribel);
			System.out.println("Cuentas de Maribel guardadas");

			// Relaciones entre clientes y cuentas
			tomas.addAccount(cuenta1Tomas);
			tomas.addAccount(cuenta2Tomas);
			maribel.addAccount(cuenta1Maribel);
			maribel.addAccount(cuenta2Maribel);

			clientRepository.save(tomas);
			clientRepository.save(maribel);
			System.out.println("Relaciones entre clientes y cuentas actualizadas");

			// Crear tarjetas
			Card debitCardMaribel = new Card(CardColor.GOLD, CardType.DEBIT, "321312312321312", "Maribel Perez", 321, java.sql.Date.valueOf(LocalDate.of(2027, 1, 1)), java.sql.Date.valueOf(LocalDate.of(2027, 1, 1)));
			cuenta1Maribel.addCard(debitCardMaribel);

			Card creditCardMaribel = new Card(CardColor.TITANIUM, CardType.CREDIT, "5555666677778888", "Maribel Perez", 456, java.sql.Date.valueOf(LocalDate.of(2025, 12, 1)), java.sql.Date.valueOf(LocalDate.of(2027, 1, 1)));
			cuenta2Maribel.addCard(creditCardMaribel);

			Card creditCardTomas = new Card(CardColor.SILVER, CardType.CREDIT, "9999000011112222", "Tomas Gonzalez", 789, java.sql.Date.valueOf(LocalDate.of(2025, 12, 1)), java.sql.Date.valueOf(LocalDate.of(2027, 1, 1)));
			cuenta1Tomas.addCard(creditCardTomas);

			Card debitCardTomas = new Card(CardColor.GOLD, CardType.DEBIT, "1234567890123456", "Tomas Gonzalez", 987, java.sql.Date.valueOf(LocalDate.of(2025, 12, 1)), java.sql.Date.valueOf(LocalDate.of(2027, 1, 1)));
			cuenta2Tomas.addCard(debitCardTomas);

			cuenta2Tomas.addCard(debitCardTomas);

			cardRepository.save(debitCardMaribel);
			cardRepository.save(creditCardMaribel);
			cardRepository.save(creditCardTomas);
			cardRepository.save(debitCardTomas);
			System.out.println("Tarjetas guardadas");

			// Relaciones entre cuentas y tarjetas
			accountRepository.save(cuenta1Tomas);
			accountRepository.save(cuenta2Tomas);
			accountRepository.save(cuenta1Maribel);
			accountRepository.save(cuenta2Maribel);
			System.out.println("Relaciones entre cuentas y tarjetas actualizadas");

			Loan loan1 = new Loan("Hipotecario", 500000.0, Arrays.asList(12, 24, 36, 48, 60));
			Loan loan2 = new Loan("Personal", 100000.0, Arrays.asList(6, 12, 24));
			Loan loan3 = new Loan("Automotriz", 300000.0, Arrays.asList(6, 12, 24, 36));

			loanRepository.saveAll(Arrays.asList(loan1, loan2, loan3));

			ClientLoan clientLoan1 = new ClientLoan(400000.0, 60);
			tomas.addClientLoan(clientLoan1);
			loan1.addClientLoan(clientLoan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(50000.0, 12);
			tomas.addClientLoan(clientLoan2);
			loan1.addClientLoan(clientLoan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(100000.0, 24);
			maribel.addClientLoan(clientLoan3);
			loan2.addClientLoan(clientLoan3);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan(200000.0, 36);
			maribel.addClientLoan(clientLoan4);
			loan3.addClientLoan(clientLoan4);
			clientLoanRepository.save(clientLoan4);

			Transaction trans1 = new Transaction (TransactionType.CREDIT, 2000.0, LocalDateTime.now(), "Crédito inicial");
			Transaction trans2 = new Transaction (TransactionType.DEBIT, -200.0, LocalDateTime.now(), "Retiro de efectivo");
			Transaction trans3 = new Transaction (TransactionType.CREDIT, 800.0, LocalDateTime.now(), "Depósito inicial");
			Transaction trans4 = new Transaction (TransactionType.DEBIT, -300.0, LocalDateTime.now(), "Compra con tarjeta");
			Transaction trans5 = new Transaction (TransactionType.CREDIT, 1000.0, LocalDateTime.now(), "Crédito inicial");
			Transaction trans6 = new Transaction (TransactionType.DEBIT, -400.0, LocalDateTime.now(), "Retiro de efectivo");
			Transaction trans7 = new Transaction (TransactionType.CREDIT, 1200.0, LocalDateTime.now(), "Depósito inicial");
			Transaction trans8 = new Transaction (TransactionType.DEBIT, -500.0, LocalDateTime.now(), "Compra con tarjeta");

			cuenta1Tomas.addTransaction(trans1);
			cuenta1Tomas.addTransaction(trans2);
			cuenta2Tomas.addTransaction(trans3);
			cuenta2Tomas.addTransaction(trans4);
			cuenta1Maribel.addTransaction(trans5);
			cuenta1Maribel.addTransaction(trans6);
			cuenta2Maribel.addTransaction(trans7);
			cuenta2Maribel.addTransaction(trans8);

			transactionRepository.saveAll(Arrays.asList(trans1, trans2, trans3, trans4, trans5, trans6, trans7, trans8));
		};
	}
}
