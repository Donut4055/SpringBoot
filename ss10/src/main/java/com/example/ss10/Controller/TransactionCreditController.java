package com.example.ss10.Controller;


import com.example.ss10.model.dto.TransactionCreditRequest;
import com.example.ss10.model.entity.TransactionCredit;
import com.example.ss10.service.TransactionCreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/credit-transactions")
@RequiredArgsConstructor
public class TransactionCreditController {
    private final TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> spendWithCreditCard(@Valid @RequestBody TransactionCreditRequest request) {
        TransactionCredit transaction = transactionCreditService.spendWithCreditCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}

