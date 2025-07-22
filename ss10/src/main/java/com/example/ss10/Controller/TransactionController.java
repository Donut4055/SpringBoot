package com.example.ss10.Controller;


import com.example.ss10.model.dto.TransactionRequest;
import com.example.ss10.model.entity.Transaction;
import com.example.ss10.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> transferMoney(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.transferMoney(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}

