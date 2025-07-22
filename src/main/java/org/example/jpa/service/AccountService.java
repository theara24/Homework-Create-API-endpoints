package org.example.jpa.service;

import org.example.jpa.dto.AccountResponse;
import org.example.jpa.dto.CreateAccountRequest;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);

}