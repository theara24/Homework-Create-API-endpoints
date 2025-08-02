package org.example.homeworkcreateendpoint.controller;

import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll();
    }

}