package org.example.homeworkcreateendpoint.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank(message = "Account number is required")
        @Size(max = 32, message = "Account number must not exceed 32 characters")
        String actNo,

        @NotBlank(message = "Currency is required")
        @Size(max = 50, message = "Currency must not exceed 50 characters")
        String actCurrency,

        @NotNull(message = "Balance is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
        BigDecimal balance,

        @NotNull(message = "Account type ID is required")
        Integer accountTypeId,

        @NotNull(message = "Customer ID is required")
        Integer customerId
) {
}