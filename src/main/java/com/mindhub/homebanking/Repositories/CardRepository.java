package com.mindhub.homebanking.Repositories;


import com.mindhub.homebanking.Enum.CardType;
import com.mindhub.homebanking.Models.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByAccountId(Long clientId);

    long countByClientIdAndType(Long client_id , CardType type);
}