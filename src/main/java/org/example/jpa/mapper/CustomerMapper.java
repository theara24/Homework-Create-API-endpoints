package org.example.jpa.mapper;

import org.example.jpa.domain.Customer;
import org.example.jpa.dto.CreateCustomerRequest;
import org.example.jpa.dto.CustomerResponse;
import org.example.jpa.dto.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);

    CustomerResponse fromCustomer(Customer customer);

    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "kyc", ignore = true)
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}