package org.example.homeworkcreateendpoint.controller;

import jakarta.validation.Valid;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.UpdateAccountRequest;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createNew(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createNew(createAccountRequest);
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{actNo}")
    public AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> findByCustomerId(@PathVariable Integer customerId) {
        return accountService.findByCustomerId(customerId);
    }

    @DeleteMapping("/{actNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByActNo(@PathVariable String actNo) {
        accountService.deleteByActNo(actNo);
    }

    @PatchMapping("/{actNo}")
    public AccountResponse updateByActNo(@PathVariable String actNo, @Valid @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateByActNo(actNo, updateAccountRequest);
    }

    @PatchMapping("/{actNo}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableByActNo(@PathVariable String actNo) {
        accountService.disableByActNo(actNo);
    }
}