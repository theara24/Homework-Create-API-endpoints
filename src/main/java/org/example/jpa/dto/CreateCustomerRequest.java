package org.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "Gender is required")
        String gender,

        String email,
        String phoneNumber,
        String remark,
        @NotBlank(message = "National card ID is required")
        String nationalCardId,
        @NotBlank(message = "Segment is required")
        String segment
) {
}