package org.example.jpa.controller;


import lombok.RequiredArgsConstructor;
import org.example.jpa.dto.AccountResponse;
import org.example.jpa.dto.CreateAccountRequest;
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
    public AccountResponse createNew(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createNew(createAccountRequest);
    }
}