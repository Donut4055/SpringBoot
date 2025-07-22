package com.example.ss10.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "credit_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(nullable = false)
    private Double spendingLimit;

    @Column(nullable = false)
    private Double amountSpent = 0.0;

    @Column(nullable = false)
    private String status = "active";
}
