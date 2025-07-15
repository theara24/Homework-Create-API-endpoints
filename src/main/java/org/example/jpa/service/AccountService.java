package org.example.jpa.service;

import org.example.jpa.domain.Account;
import org.example.jpa.domain.Customer;
import org.example.jpa.repository.AccountRepository;
import org.example.jpa.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Account createNewAccount(String phoneNumber, String actType, String actCurrency) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found"));

        Account account = new Account();
        account.setActNo(UUID.randomUUID().toString());
        account.setActType(actType);
        account.setActCurrency(actCurrency);
        account.setBalance(BigDecimal.ZERO);
        account.setIsDeleted(false);
        account.setCustomer(customer);

        // Set overLimit based on customer segment
        BigDecimal overLimit;
        switch (customer.getSegment().toLowerCase()) {
            case "gold":
                overLimit = new BigDecimal("50000");
                break;
            case "silver":
                overLimit = new BigDecimal("10000");
                break;
            case "regular":
                overLimit = new BigDecimal("5000");
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customer segment");
        }
        account.setOverLimit(overLimit);

        return accountRepository.save(account);
    }
}