package org.example.jpa.mapper;

import org.example.jpa.domain.Account;
import org.example.jpa.dto.AccountResponse;
import org.example.jpa.dto.CreateAccountRequest;
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