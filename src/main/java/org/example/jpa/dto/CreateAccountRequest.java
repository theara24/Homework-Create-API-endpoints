package org.example.jpa.dto;

import org.example.jpa.util.CurrencyUtil;

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