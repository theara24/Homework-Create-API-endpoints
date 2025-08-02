package org.example.homeworkcreateendpoint.mapper;

import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.dto.CreateCustomerRequest;
import org.example.homeworkcreateendpoint.dto.CustomerResponse;
import org.example.homeworkcreateendpoint.dto.UpdateCustomerRequest;
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