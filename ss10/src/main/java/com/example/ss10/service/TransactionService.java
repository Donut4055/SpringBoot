package com.example.ss10.service;


import com.example.ss10.exception.BadRequestException;
import com.example.ss10.model.dto.TransactionRequest;
import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.Transaction;
import com.example.ss10.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final NotificationService notificationService;

    @Transactional
    public Transaction transferMoney(TransactionRequest request) {
        Account sender = accountService.getAccountById(request.getSenderId());
        Account receiver = accountService.getAccountById(request.getReceiverId());

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setMoney(request.getMoney());
        transaction.setNote(request.getNote());

        try {
            if (sender.getMoney() < request.getMoney()) {
                transaction.setStatus("thất bại");
                transactionRepository.save(transaction);
                log.error("Transaction failed - Insufficient funds. Transaction: {}", transaction);
                throw new BadRequestException("Insufficient funds");
            }

            if (sender.getStatus() != Account.AccountStatus.ACTIVE) {
                transaction.setStatus("thất bại");
                transactionRepository.save(transaction);
                log.error("Transaction failed - Sender account inactive. Transaction: {}", transaction);
                throw new BadRequestException("Sender account is inactive");
            }

            if (receiver.getStatus() != Account.AccountStatus.ACTIVE) {
                transaction.setStatus("thất bại");
                transactionRepository.save(transaction);
                log.error("Transaction failed - Receiver account inactive. Transaction: {}", transaction);
                throw new BadRequestException("Receiver account is inactive");
            }

            sender.setMoney(sender.getMoney() - request.getMoney());
            receiver.setMoney(receiver.getMoney() + request.getMoney());

            transaction.setStatus("thành công");
            Transaction savedTransaction = transactionRepository.save(transaction);

            String senderNotification = String.format("Bạn đã chuyển %.2f VND cho %s. Số dư hiện tại: %.2f VND",
                    request.getMoney(), receiver.getFullName(), sender.getMoney());
            String receiverNotification = String.format("Bạn đã nhận %.2f VND từ %s. Số dư hiện tại: %.2f VND",
                    request.getMoney(), sender.getFullName(), receiver.getMoney());

            notificationService.createNotification(sender, senderNotification);
            notificationService.createNotification(receiver, receiverNotification);

            log.info("Transaction successful: {}", savedTransaction);
            return savedTransaction;

        } catch (Exception e) {
            transaction.setStatus("thất bại");
            transactionRepository.save(transaction);
            log.error("Transaction failed with error: {}", e.getMessage());
            throw e;
        }
    }
}

