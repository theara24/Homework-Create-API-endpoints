package org.example.homeworkcreateendpoint.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.example.homeworkcreateendpoint.domain.Account;
import org.example.homeworkcreateendpoint.domain.AccountType;
import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.UpdateAccountRequest;
import org.example.homeworkcreateendpoint.mapper.AccountMapper;
import org.example.homeworkcreateendpoint.repository.AccountRepository;
import org.example.homeworkcreateendpoint.repository.AccountTypeRepository;
import org.example.homeworkcreateendpoint.repository.CustomerRepository;
import org.example.homeworkcreateendpoint.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNew(CreateAccountRequest createAccountRequest) {
        if (accountRepository.existsByActNo(createAccountRequest.actNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account number already exists");
        }

        AccountType accountType = accountTypeRepository.findById(createAccountRequest.accountTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account type not found"));

        Customer customer = customerRepository.findById(createAccountRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Account account = accountMapper.toAccount(createAccountRequest);
        account.setIsDeleted(false);
        account.setAccountType(accountType);
        account.setCustomer(customer);

        log.info("Account before save: {}", account.getId());
        account = accountRepository.save(account);
        log.info("Account after save: {}", account.getId());

        return accountMapper.fromAccount(account);
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

    @Override
    public AccountResponse findByActNo(String actNo) {
        return accountRepository.findByActNo(actNo)
                .map(accountMapper::fromAccount)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with actNo: " + actNo));
    }

    @Override
    public List<AccountResponse> findByCustomerId(Integer customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return accountRepository.findByCustomerId(customerId).stream()
                .map(accountMapper::fromAccount)
                .toList();
    }

    @Override
    public void deleteByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with actNo: " + actNo));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponse updateByActNo(String actNo, UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with actNo: " + actNo));

        if (updateAccountRequest.accountTypeId() != null) {
            AccountType accountType = accountTypeRepository.findById(updateAccountRequest.accountTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account type not found"));
            account.setAccountType(accountType);
        }

        accountMapper.toAccountPartially(updateAccountRequest, account);
        account = accountRepository.save(account);
        return accountMapper.fromAccount(account);
    }

    @Override
    public void disableByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with actNo: " + actNo));
        account.setIsDeleted(true);
        accountRepository.save(account);
    }
}