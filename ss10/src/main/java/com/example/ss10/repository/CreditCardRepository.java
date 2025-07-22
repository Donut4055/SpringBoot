package com.example.ss10.repository;


import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccount(Account account);
    boolean existsByAccount(Account account);
}

