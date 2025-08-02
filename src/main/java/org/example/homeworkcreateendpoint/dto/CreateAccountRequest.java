package org.example.homeworkcreateendpoint.dto;

import org.example.homeworkcreateendpoint.util.CurrencyUtil;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String actNo,
        String actName,
        CurrencyUtil actCurrency,
        BigDecimal balance,
        String phoneNumber,
        String accountType
) {
}