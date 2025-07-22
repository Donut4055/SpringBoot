package com.example.ss10.model.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone must be 10-11 digits")
    private String phone;

    @NotBlank(message = "CCCD is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "CCCD must be 12 digits")
    private String cccd;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Money is required")
    @Min(value = 0, message = "Money must be greater than or equal to 0")
    private Double money;
}

