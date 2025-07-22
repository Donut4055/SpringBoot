package com.example.ss10.model.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.UUID;

@Data
public class TransactionRequest {
    @NotNull(message = "Receiver ID is required")
    private UUID receiverId;

    @NotNull(message = "Sender ID is required")
    private UUID senderId;

    @NotNull(message = "Money is required")
    @DecimalMin(value = "0.01", message = "Money must be greater than 0")
    private Double money;

    private String note;
}
