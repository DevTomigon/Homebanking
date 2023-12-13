package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll ()
                .stream ()
                .map ( AccountDTO::new )
                .collect ( Collectors.toList () );
    }

    @GetMapping("{id}")
    public AccountDTO getOneAccount(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(account -> new AccountDTO(account)) //
                .orElse(null);
    }
}