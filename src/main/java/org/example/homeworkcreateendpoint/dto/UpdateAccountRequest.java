package org.example.homeworkcreateendpoint.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UpdateAccountRequest(
        @Size(max = 50, message = "Currency must not exceed 50 characters")
        String actCurrency,

        @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
        BigDecimal balance,

        Integer accountTypeId
) {
}