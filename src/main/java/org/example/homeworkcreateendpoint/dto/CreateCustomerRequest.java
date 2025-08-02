package org.example.homeworkcreateendpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateCustomerRequest(

        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Date of birth is required")
        LocalDate dob,

        String email,
        String phoneNumber,
        String remark,

        String nationalCardId,
        String customerSegment
) {
}