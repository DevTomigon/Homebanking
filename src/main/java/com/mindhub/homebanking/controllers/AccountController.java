package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountDTO getOneAccount(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(AccountDTO::new)
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> createAccount(Authentication authentication){
        Client client = clientRepository.findByEmail ( authentication.getName () );

        if (client.getAccounts ().size () >= 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Client has reached the maximum number of accounts (2)");
        }

        String number;
        
        do {
            number = "VIN-" + getRandomNumber ( 0 , 8 );
        }while(accountRepository.existsByNumber ( number ));

        Account account = new Account (number, LocalDate.now (), 0.0 );
        client.addAccount ( account );
        accountRepository.save(account);
        return new ResponseEntity<> ( "Cuenta creada con exito" + client.getFirstName (), HttpStatus.CREATED );
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}






