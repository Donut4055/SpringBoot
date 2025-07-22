package com.example.ss10.model.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class TransactionCreditRequest {
    @NotNull(message = "Account receiver ID is required")
    private UUID accountReceiverId;

    @NotNull(message = "Credit card sender ID is required")
    private UUID creditCardSenderId;

    @NotNull(message = "Money is required")
    @DecimalMin(value = "0.01", message = "Money must be greater than 0")
    private Double money;

    private String note;
}
