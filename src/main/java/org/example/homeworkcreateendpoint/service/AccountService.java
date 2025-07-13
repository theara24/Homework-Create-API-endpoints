package org.example.homeworkcreateendpoint.service;

import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAll();

    AccountResponse findByActNo(String actNo);

    List<AccountResponse> findByCustomerId(Integer customerId);

    void deleteByActNo(String actNo);

    AccountResponse updateByActNo(String actNo, UpdateAccountRequest updateAccountRequest);

    void disableByActNo(String actNo);
}