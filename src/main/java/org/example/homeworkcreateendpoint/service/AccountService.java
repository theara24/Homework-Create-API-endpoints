package org.example.homeworkcreateendpoint.service;

import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAll();

}