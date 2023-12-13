package com.mindhub.homebanking.Repositories;
import com.mindhub.homebanking.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByAccountId(Long accountId);
}
