package com.example.ss10.service;


import com.example.ss10.exception.ResourceNotFoundException;
import com.example.ss10.exception.BadRequestException;
import com.example.ss10.model.dto.AccountRequest;
import com.example.ss10.model.entity.Account;
import com.example.ss10.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(AccountRequest request) {
        if (accountRepository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Phone number already exists");
        }
        if (accountRepository.existsByCccd(request.getCccd())) {
            throw new BadRequestException("CCCD already exists");
        }
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        Account account = new Account();
        account.setFullName(request.getFullName());
        account.setPhone(request.getPhone());
        account.setCccd(request.getCccd());
        account.setEmail(request.getEmail());
        account.setMoney(request.getMoney());
        account.setStatus(Account.AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);
        log.info("Created new account: {}", savedAccount);
        return savedAccount;
    }

    @Transactional
    public Account updateAccount(UUID id, AccountRequest request) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

        log.info("Updating account - Old info: {}", existingAccount);

        if (!existingAccount.getPhone().equals(request.getPhone())
                && accountRepository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Phone number already exists");
        }
        if (!existingAccount.getCccd().equals(request.getCccd())
                && accountRepository.existsByCccd(request.getCccd())) {
            throw new BadRequestException("CCCD already exists");
        }
        if (!existingAccount.getEmail().equals(request.getEmail())
                && accountRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        existingAccount.setFullName(request.getFullName());
        existingAccount.setPhone(request.getPhone());
        existingAccount.setCccd(request.getCccd());
        existingAccount.setEmail(request.getEmail());
        existingAccount.setMoney(request.getMoney());

        Account updatedAccount = accountRepository.save(existingAccount);

        log.info("Account updated - New info: {}", updatedAccount);

        return updatedAccount;
    }

    @Transactional
    public void closeAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

        account.setStatus(Account.AccountStatus.INACTIVE);
        accountRepository.save(account);

        log.info("Closed account: {}", account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByCccd(String cccd) {
        return accountRepository.findByCccd(cccd)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with CCCD: " + cccd));
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }
}
