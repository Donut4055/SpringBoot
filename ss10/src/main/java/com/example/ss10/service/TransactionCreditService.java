package com.example.ss10.service;


import com.example.ss10.exception.BadRequestException;
import com.example.ss10.model.dto.TransactionCreditRequest;
import com.example.ss10.model.entity.Account;
import com.example.ss10.model.entity.CreditCard;
import com.example.ss10.model.entity.TransactionCredit;
import com.example.ss10.repository.TransactionCreditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionCreditService {
    private final TransactionCreditRepository transactionCreditRepository;
    private final AccountService accountService;
    private final CreditCardService creditCardService;
    private final NotificationService notificationService;

    @Transactional
    public TransactionCredit spendWithCreditCard(TransactionCreditRequest request) {
        Account receiver = accountService.getAccountById(request.getAccountReceiverId());
        CreditCard creditCard = creditCardService.getCreditCardById(request.getCreditCardSenderId());

        TransactionCredit transaction = new TransactionCredit();
        transaction.setAccountReceiver(receiver);
        transaction.setCreditCardSender(creditCard);
        transaction.setMoney(request.getMoney());
        transaction.setNote(request.getNote());

        try {
            if (!"active".equals(creditCard.getStatus())) {
                transaction.setStatus("thất bại");
                transactionCreditRepository.save(transaction);
                log.error("Credit transaction failed - Credit card inactive. Transaction: {}", transaction);
                throw new BadRequestException("Credit card is inactive");
            }

            if (creditCard.getAmountSpent() + request.getMoney() > creditCard.getSpendingLimit()) {
                transaction.setStatus("thất bại");
                transactionCreditRepository.save(transaction);
                log.error("Credit transaction failed - Exceeds spending limit. Current spent: {}, Amount: {}, Limit: {}",
                        creditCard.getAmountSpent(), request.getMoney(), creditCard.getSpendingLimit());
                throw new BadRequestException("Transaction amount exceeds credit limit");
            }

            if (receiver.getStatus() != Account.AccountStatus.ACTIVE) {
                transaction.setStatus("thất bại");
                transactionCreditRepository.save(transaction);
                log.error("Credit transaction failed - Receiver account inactive. Transaction: {}", transaction);
                throw new BadRequestException("Receiver account is inactive");
            }

            creditCard.setAmountSpent(creditCard.getAmountSpent() + request.getMoney());
            receiver.setMoney(receiver.getMoney() + request.getMoney());

            transaction.setStatus("thành công");
            TransactionCredit savedTransaction = transactionCreditRepository.save(transaction);

            String senderNotification = String.format("Bạn đã chi tiêu %.2f VND bằng thẻ tín dụng. Đã sử dụng: %.2f/%.2f VND",
                    request.getMoney(), creditCard.getAmountSpent(), creditCard.getSpendingLimit());
            String receiverNotification = String.format("Bạn đã nhận %.2f VND từ giao dịch thẻ tín dụng. Số dư hiện tại: %.2f VND",
                    request.getMoney(), receiver.getMoney());

            notificationService.createNotification(creditCard.getAccount(), senderNotification);
            notificationService.createNotification(receiver, receiverNotification);

            log.info("Credit transaction successful: {}", savedTransaction);
            return savedTransaction;

        } catch (Exception e) {
            transaction.setStatus("thất bại");
            transactionCreditRepository.save(transaction);
            log.error("Credit transaction failed with error: {}", e.getMessage());
            throw e;
        }
    }
}
