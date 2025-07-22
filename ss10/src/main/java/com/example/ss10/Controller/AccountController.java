package com.example.ss10.Controller;


import com.example.ss10.model.dto.AccountRequest;
import com.example.ss10.model.entity.Account;
import com.example.ss10.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequest request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id,
                                                 @Valid @RequestBody AccountRequest request) {
        Account account = accountService.updateAccount(id, request);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable UUID id) {
        accountService.closeAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Account> getAccountByCccd(@PathVariable String cccd) {
        Account account = accountService.getAccountByCccd(cccd);
        return ResponseEntity.ok(account);
    }
}

