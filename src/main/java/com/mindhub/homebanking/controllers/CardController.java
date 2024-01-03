package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.Enum.CardType;
import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Repositories.CardRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import com.mindhub.homebanking.dto.CardDTO;
import com.mindhub.homebanking.Enum.CardColor;
import com.mindhub.homebanking.Enum.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/current")
public class CardController {

    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CardController(CardRepository cardRepository, ClientRepository clientRepository) {
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/cards")
    public ResponseEntity<String> createCard(@RequestParam CardColor color, @RequestParam CardType type,
                                             Authentication authentication) {

        // Obtener información del cliente autenticado
        String clientEmail = authentication.getName();
        Client client = clientRepository.findByEmailIgnoreCase(clientEmail);

        // Verificar límite de tarjetas
        long existingCards = cardRepository.countByClientIdAndType(client.getId(), type);
        if (existingCards >= 3) {
            return new ResponseEntity<>("403 Forbidden: The client already has 3 cards of the specified type.", HttpStatus.FORBIDDEN);
        }

        // Generar datos de la tarjeta
        String cardNumber = generateCardNumber();
        String cardHolder = client.getFirstName() + " " + client.getLastName();
        int cvv = Integer.parseInt ( generateRandomCVV() );
        // Fecha de inicio es la fecha actual
        java.sql.Date startDate = new java.sql.Date(System.currentTimeMillis());
        // Fecha de vencimiento es la fecha actual más 5 años
        java.sql.Date expirationDate = new java.sql.Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000));

        // Crear tarjeta y asignarla al cliente
        Card card = new Card(color, type, cardNumber, cardHolder, cvv, startDate, expirationDate);
        card.setClient(client);
        cardRepository.save(card);

        return new ResponseEntity<>("201 Created: Card has been successfully created.", HttpStatus.CREATED);
    }

    @GetMapping("/cards")
    public List<CardDTO> getAllCards(Authentication authentication) {
        String clientEmail = authentication.getName();
        Client client = clientRepository.findByEmailIgnoreCase(clientEmail);
        List<Card> cards = cardRepository.findByAccountId(client.getId());
        return cards.stream()
                .map(CardDTO::new)
                .collect(Collectors.toList());
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            cardNumber.append(String.format("%04d", random.nextInt(10000)));
            if (i < 3) {
                cardNumber.append("-");
            }
        }
        return cardNumber.toString();
    }

    private String generateRandomCVV() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(1000));
    }
}
