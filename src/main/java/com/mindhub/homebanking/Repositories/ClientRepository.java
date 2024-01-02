package com.mindhub.homebanking.Repositories;

import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.dto.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmailIgnoreCase (String email);

    Client findByFirstNameAndLastName( String firtsName, String lastName);

    int countByFirstName(String nombre);

    boolean existsByEmail(String email);


}
