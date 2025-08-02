package org.example.homeworkcreateendpoint.mapper;

import org.example.homeworkcreateendpoint.domain.Account;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "actCurrency", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountType.type", target = "accountType")
    AccountResponse fromAccount(Account account);
}