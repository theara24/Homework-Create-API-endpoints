package org.example.jpa.dto;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
