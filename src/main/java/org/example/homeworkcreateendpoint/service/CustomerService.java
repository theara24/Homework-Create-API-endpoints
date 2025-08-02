package org.example.homeworkcreateendpoint.service;

import org.example.homeworkcreateendpoint.dto.CreateCustomerRequest;
import org.example.homeworkcreateendpoint.dto.CustomerResponse;
import org.example.homeworkcreateendpoint.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void disableByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    List<CustomerResponse> findAll();

}