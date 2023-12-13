package com.mindhub.homebanking;

import com.mindhub.homebanking.Enum.TransactionType;
import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.Transactions;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import com.mindhub.homebanking.Repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
		return args -> {
		// Crear clientes
		Client tomas = new Client ("Tomas", "Gonzalez", "tomigonzalez898@gmail.com");
		Client maribel = new Client("Maribel", "Perez", "mariperez322@gmail.com");

		// Guardar clientes
		clientRepository.save(tomas);
		clientRepository.save(maribel);

		// Crear cuentas para Tomas
		Account cuenta1Tomas = new Account( "VIN001", LocalDate.now(), 5000.0);
		Account cuenta2Tomas = new Account( "VIN002", LocalDate.now().plusDays(1), 7500.0);

		// Crear cuentas para Maribel
		Account cuenta1Maribel = new Account("VIN003", LocalDate.now(), 10000.0);
		Account cuenta2Maribel = new Account("VIN004", LocalDate.now().plusDays(1), 15000.0);
		// Guardar cuentas


		// Relacionar cuentas con clientes
		tomas.addAccount(cuenta1Tomas);
		tomas.addAccount(cuenta2Tomas);
		maribel.addAccount(cuenta1Maribel);
		maribel.addAccount(cuenta2Maribel);

		accountRepository.save(cuenta1Tomas);
		accountRepository.save(cuenta2Tomas);
		accountRepository.save(cuenta1Maribel);
		accountRepository.save(cuenta2Maribel);


		// Crear transacciones para cuenta1Tomas
		Transactions trans1 = new Transactions ( TransactionType.CREDIT, 2000.0, LocalDateTime.now(), "Crédito inicial");
		Transactions trans2 = new Transactions (TransactionType.DEBIT, -200.0, LocalDateTime.now(), "Retiro de efectivo");

		// Crear transacciones para cuenta2Tomas
		Transactions trans3 = new Transactions ( TransactionType.CREDIT, 800.0, LocalDateTime.now(), "Depósito inicial");
		Transactions trans4 = new Transactions (TransactionType.DEBIT, -300.0, LocalDateTime.now(), "Compra con tarjeta");

		// Crear transacciones para cuenta1Maribel
		Transactions trans5 = new Transactions (TransactionType.CREDIT, 1000.0, LocalDateTime.now(), "Crédito inicial");
		Transactions trans6 = new Transactions (TransactionType.DEBIT, -400.0, LocalDateTime.now(), "Retiro de efectivo");

		// Crear transacciones para cuenta2Maribel
		Transactions trans7 = new Transactions (TransactionType.CREDIT, 1200.0, LocalDateTime.now(), "Depósito inicial");
		Transactions trans8 = new Transactions (TransactionType.DEBIT, -500.0, LocalDateTime.now(), "Compra con tarjeta");

		cuenta1Tomas.addTransaction(trans1);
		cuenta1Tomas.addTransaction(trans2);
		cuenta2Tomas.addTransaction(trans3);
		cuenta2Tomas.addTransaction(trans4);
		cuenta1Maribel.addTransaction(trans5);
		cuenta1Maribel.addTransaction(trans6);
		cuenta2Maribel.addTransaction(trans7);
		cuenta2Maribel.addTransaction(trans8);

		// Guardar transacciones
		transactionRepository.save(trans1);
		transactionRepository.save(trans2);
		transactionRepository.save(trans3);
		transactionRepository.save(trans4);
		transactionRepository.save(trans5);
		transactionRepository.save(trans6);
		transactionRepository.save(trans7);
		transactionRepository.save(trans8);

		// Relacionar transacciones con cuentas



		};
	}
}
