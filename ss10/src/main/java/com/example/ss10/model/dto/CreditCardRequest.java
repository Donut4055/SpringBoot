package com.example.ss10.model.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class CreditCardRequest {
    @NotNull(message = "Account ID is required")
    private UUID accountId;

    @NotNull(message = "Spending limit is required")
    @DecimalMin(value = "0.01", message = "Spending limit must be greater than 0")
    private Double spendingLimit;
}

