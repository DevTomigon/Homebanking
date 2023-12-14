package com.mindhub.homebanking;

import com.mindhub.homebanking.Enum.TransactionType;
import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientLoanRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import com.mindhub.homebanking.Repositories.LoanRepository;
import com.mindhub.homebanking.Repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
									  TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository) {
		return args -> {
			// Crear clientes
			Client tomas = new Client("Tomas", "Gonzalez", "tomigonzalez898@gmail.com");
			Client maribel = new Client("Maribel", "Perez", "mariperez322@gmail.com");

			// Guardar clientes
			clientRepository.saveAll(Arrays.asList(tomas, maribel));

			// Crear cuentas para Tomas
			Account cuenta1Tomas = new Account("VIN001", LocalDate.now(), 5000.0);
			Account cuenta2Tomas = new Account("VIN002", LocalDate.now().plusDays(1), 7500.0);

			// Crear cuentas para Maribel
			Account cuenta1Maribel = new Account("VIN003", LocalDate.now(), 10000.0);
			Account cuenta2Maribel = new Account("VIN004", LocalDate.now().plusDays(1), 15000.0);

			// Guardar cuentas
			accountRepository.saveAll(Arrays.asList(cuenta1Tomas, cuenta2Tomas, cuenta1Maribel, cuenta2Maribel));

			// Relacionar cuentas con clientes
			tomas.addAccount(cuenta1Tomas);
			tomas.addAccount(cuenta2Tomas);
			maribel.addAccount(cuenta1Maribel);
			maribel.addAccount(cuenta2Maribel);

			// Guardar relaciones entre cuentas y clientes
			accountRepository.saveAll(Arrays.asList(cuenta1Tomas, cuenta2Tomas, cuenta1Maribel, cuenta2Maribel));

			// Crear préstamos
			Loan loan1 = new Loan("Hipotecario",  500.000, Arrays.asList(12,24,36,48,60));
			Loan loan2 = new Loan("Personal", 100.000, Arrays.asList(6,12,24));
			Loan loan3 = new Loan("Automotriz",  300.000, Arrays.asList( 6,12,24,36));

			// Guardar préstamos
			loanRepository.saveAll(Arrays.asList(loan1, loan2, loan3));

			// Asociar 2 préstamos a cada cliente
			ClientLoan clientLoan1 = new ClientLoan(400.000, 60);


			tomas.addClientLoan(clientLoan1);
			loan1.addClientLoan(clientLoan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(50000.00, 12);


			tomas.addClientLoan(clientLoan2);
			loan1.addClientLoan(clientLoan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(100000.00, 24);


			maribel.addClientLoan(clientLoan3);
			loan2.addClientLoan(clientLoan3);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan( 200000.00, 36);


			maribel.addClientLoan(clientLoan4);
			loan3.addClientLoan (clientLoan4);
			clientLoanRepository.save(clientLoan4);

			// Crear transacciones para cuenta1Tomas
			Transactions trans1 = new Transactions(TransactionType.CREDIT, 2000.0, LocalDateTime.now(), "Crédito inicial");
			Transactions trans2 = new Transactions(TransactionType.DEBIT, -200.0, LocalDateTime.now(), "Retiro de efectivo");

			// Crear transacciones para cuenta2Tomas
			Transactions trans3 = new Transactions(TransactionType.CREDIT, 800.0, LocalDateTime.now(), "Depósito inicial");
			Transactions trans4 = new Transactions(TransactionType.DEBIT, -300.0, LocalDateTime.now(), "Compra con tarjeta");

			// Crear transacciones para cuenta1Maribel
			Transactions trans5 = new Transactions(TransactionType.CREDIT, 1000.0, LocalDateTime.now(), "Crédito inicial");
			Transactions trans6 = new Transactions(TransactionType.DEBIT, -400.0, LocalDateTime.now(), "Retiro de efectivo");

			// Crear transacciones para cuenta2Maribel
			Transactions trans7 = new Transactions(TransactionType.CREDIT, 1200.0, LocalDateTime.now(), "Depósito inicial");
			Transactions trans8 = new Transactions(TransactionType.DEBIT, -500.0, LocalDateTime.now(), "Compra con tarjeta");

			cuenta1Tomas.addTransaction(trans1);
			cuenta1Tomas.addTransaction(trans2);
			cuenta2Tomas.addTransaction(trans3);
			cuenta2Tomas.addTransaction(trans4);
			cuenta1Maribel.addTransaction(trans5);
			cuenta1Maribel.addTransaction(trans6);
			cuenta2Maribel.addTransaction(trans7);
			cuenta2Maribel.addTransaction(trans8);

			// Guardar transacciones
			transactionRepository.saveAll(Arrays.asList(trans1, trans2, trans3, trans4, trans5, trans6, trans7, trans8));

			// Relacionar transacciones con cuentas
		};
	}
}
