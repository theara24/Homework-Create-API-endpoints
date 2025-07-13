package org.example.homeworkcreateendpoint.mapper;

import org.example.homeworkcreateendpoint.domain.Account;
import org.example.homeworkcreateendpoint.dto.CreateAccountRequest;
import org.example.homeworkcreateendpoint.dto.AccountResponse;
import org.example.homeworkcreateendpoint.dto.UpdateAccountRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    @Mapping(source = "accountType.typeName", target = "accountTypeName")
    @Mapping(source = "customer.fullName", target = "customerName")
    @Mapping(source = "balance", target = "balance", numberFormat = "$#.##")
    AccountResponse fromAccount(Account account);

    Account toAccount(CreateAccountRequest createAccountRequest);
}