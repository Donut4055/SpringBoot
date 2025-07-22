package com.example.ss10.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction_credits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_receiver_id", nullable = false)
    private Account accountReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_sender_id", nullable = false)
    private CreditCard creditCardSender;

    private String note;

    @Column(nullable = false)
    private Double money;

    @Column(nullable = false)
    private String status = "đang chờ xử lý";

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

