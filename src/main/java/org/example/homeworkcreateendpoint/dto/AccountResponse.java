package org.example.homeworkcreateendpoint.dto;

import lombok.Builder;

@Builder
public record AccountResponse(
        String actNo,
        String actCurrency,
        String balance,
        String accountTypeName,
        String customerName,
        Boolean isDeleted
) {
}