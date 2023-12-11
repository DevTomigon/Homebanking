package com.mindhub.demo.controllers;
import com.mindhub.demo.Models.Client;
import com.mindhub.demo.Repositories.ClientRepository;

import com.mindhub.demo.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/clients")
    public List<ClientDTO> getAllClient() {
        return clientRepository.findAll()
                .stream()
                .map((Client client) -> new ClientDTO(client))
                .collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO findClient(@PathVariable Long id) {
        return clientRepository.findById(id).map((Client client) -> new ClientDTO(client)).orElse(null);
    }

}
