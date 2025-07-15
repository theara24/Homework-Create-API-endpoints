package org.example.jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.Account;
import org.example.jpa.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Account createNew(
            @RequestParam String phoneNumber,
            @RequestParam String actType,
            @RequestParam String actCurrency) {
        return accountService.createNewAccount(phoneNumber, actType, actCurrency);
    }
}