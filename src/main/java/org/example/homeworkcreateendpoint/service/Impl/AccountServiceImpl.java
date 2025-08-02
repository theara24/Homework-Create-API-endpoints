package org.example.homeworkcreateendpoint.service.Impl;

import org.example.homeworkcreateendpoint.domain.Account;
import org.example.homeworkcreateendpoint.domain.AccountType;
import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.mapper.AccountMapper;
import org.example.homeworkcreateendpoint.repository.AccountRepository;
import org.example.homeworkcreateendpoint.repository.AccountTypeRepository;
import org.example.homeworkcreateendpoint.repository.CustomerRepository;
import org.example.homeworkcreateendpoint.service.AccountService;
import org.example.homeworkcreateendpoint.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNew(CreateAccountRequest createAccountRequest) {

        Customer customer = customerRepository
                .findByPhoneNumber(createAccountRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer phone number not found"));

        AccountType accountType = accountTypeRepository
                .findByType(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account type not found"));

        Account account = accountMapper.toAccount(createAccountRequest);
        account.setAccountType(accountType);
        account.setCustomer(customer);

        if (account.getActNo().isBlank()) { // Auto generate
            String actNo;
            do {
                actNo = String.format("%09d", new Random().nextInt(1_000_000_000)); // Max: 999,999,999
            } while (accountRepository.existsByActNo(actNo));
            account.setActNo(actNo);
        } else { // From DTO, check validation actNo
            if (accountRepository.existsByActNo(account.getActNo())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Account number already exists");
            }
        }

        account.setIsHide(false);
        account.setIsDeleted(false);
        account.setActCurrency(createAccountRequest.actCurrency().name());

        if (account.getCustomer().getCustomerSegment().getSegment().equals("REGULAR")) {
            account.setOverLimit(BigDecimal.valueOf(5000));
        } else if (account.getCustomer().getCustomerSegment().getSegment().equals("SILVER")) {
            account.setOverLimit(BigDecimal.valueOf(50000));
        } else {
            account.setOverLimit(BigDecimal.valueOf(100000));
        }

        // Validate balance
        switch (createAccountRequest.actCurrency()) {
            case CurrencyUtil.DOLLAR -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.TEN) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 10 dollars");
                }
            }
            case CurrencyUtil.RIEL -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 40000 riels");
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Currency is not available");
        }

        account = accountRepository.save(account);

        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

}