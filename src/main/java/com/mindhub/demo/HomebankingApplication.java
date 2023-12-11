package com.mindhub.demo;

import com.mindhub.demo.Models.Account;
import com.mindhub.demo.Models.Client;
import com.mindhub.demo.Repositories.AccountRepository;
import com.mindhub.demo.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return args -> {
			Client tomas = new Client("Tomas", "Gonzalez", "tomigonzalez898@gmail.com");
			Client maribel = new Client("Maribel", "Perez", "mariperez322@gmail.com");

			Account cuenta1Tomas = new Account("VIN001", LocalDate.now(), 5.000);
			Account cuenta2Tomas = new Account("VIN002", LocalDate.now().plusDays(1), 7.500);

			Account cuenta1Maribel = new Account("VIN003", LocalDate.now(), 10.000);
			Account cuenta2Maribel = new Account("VIN004", LocalDate.now().plusDays(1), 15.000);

			clientRepository.save(tomas);
			clientRepository.save(maribel);

			cuenta1Tomas.setClient(tomas);
			cuenta2Tomas.setClient(tomas);

			cuenta1Maribel.setClient(maribel);
			cuenta2Maribel.setClient(maribel);

			accountRepository.save(cuenta1Tomas);
			accountRepository.save(cuenta2Tomas);
			accountRepository.save(cuenta1Maribel);
			accountRepository.save(cuenta2Maribel);

			tomas.addAccount(cuenta1Tomas);
			tomas.addAccount(cuenta2Tomas);

			maribel.addAccount(cuenta1Maribel);
			maribel.addAccount(cuenta2Maribel);
		};
	}



}


