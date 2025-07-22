package com.example.ss10.service;


import com.example.ss10.exception.BadRequestException;
import com.example.ss10.exception.ResourceNotFoundException;
import com.example.ss10.model.dto.CreditCardRequest;
import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.CreditCard;
import com.example.ss10.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final AccountService accountService;

    @Transactional
    public CreditCard createCreditCard(CreditCardRequest request) {
        Account account = accountService.getAccountById(request.getAccountId());

        if (creditCardRepository.existsByAccount(account)) {
            throw new BadRequestException("Account already has a credit card");
        }

        CreditCard creditCard = new CreditCard();
        creditCard.setAccount(account);
        creditCard.setSpendingLimit(request.getSpendingLimit());
        creditCard.setAmountSpent(0.0);
        creditCard.setStatus("active");

        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        log.info("Created new credit card: {}", savedCreditCard);
        return savedCreditCard;
    }

    @Transactional
    public CreditCard updateSpendingLimit(UUID id, Double newLimit) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));

        if (newLimit <= 0) {
            throw new BadRequestException("Spending limit must be greater than 0");
        }

        creditCard.setSpendingLimit(newLimit);
        CreditCard updatedCreditCard = creditCardRepository.save(creditCard);

        log.info("Updated credit card spending limit: {}", updatedCreditCard);
        return updatedCreditCard;
    }

    @Transactional
    public CreditCard updateStatus(UUID id, String status) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));

        if (!status.equals("active") && !status.equals("inactive")) {
            throw new BadRequestException("Status must be 'active' or 'inactive'");
        }

        creditCard.setStatus(status);
        CreditCard updatedCreditCard = creditCardRepository.save(creditCard);

        log.info("Updated credit card status: {}", updatedCreditCard);
        return updatedCreditCard;
    }

    public CreditCard getCreditCardById(UUID id) {
        return creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));
    }
}
