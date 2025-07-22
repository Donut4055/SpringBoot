package com.example.ss10.repository;


import com.example.ss10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, UUID> {
}

