package org.example.jpa.mapper;

import org.example.jpa.domain.Customer;
import org.example.jpa.dto.CreateCustomerRequest;
import org.example.jpa.dto.CustomerResponse;
import org.example.jpa.dto.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Partially update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest,
                             @MappingTarget Customer customer);

    // DTO -> Model
    // Model -> DTO
    // return type is converted | target data
    // parameter is source data
    CustomerResponse fromCustomer(Customer customer);

    @Mapping(source = "customerSegment", target = "customerSegment.segment")
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}