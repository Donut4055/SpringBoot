package com.example.ss10.Controller;


import com.example.ss10.model.dto.CreditCardRequest;
import com.example.ss10.model.entity.CreditCard;
import com.example.ss10.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<  CreditCard> createCreditCard(@Valid @RequestBody CreditCardRequest request) {
        CreditCard creditCard = creditCardService.createCreditCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateSpendingLimit(@PathVariable UUID id,
                                                          @RequestBody Map<String, Double> request) {
        Double newLimit = request.get("spendingLimit");
        if (newLimit == null) {
            throw new IllegalArgumentException("Spending limit is required");
        }

        CreditCard creditCard = creditCardService.updateSpendingLimit(id, newLimit);
        return ResponseEntity.ok(creditCard);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(@PathVariable UUID id,
                                                   @RequestBody Map<String, String> request) {
        String status = request.get("status");
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }

        CreditCard creditCard = creditCardService.updateStatus(id, status);
        return ResponseEntity.ok(creditCard);
    }
}

