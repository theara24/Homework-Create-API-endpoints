package org.example.homeworkcreateendpoint.service.Impl;

import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.domain.CustomerSegment;
import org.example.homeworkcreateendpoint.domain.KYC;
import org.example.homeworkcreateendpoint.dto.CreateCustomerRequest;
import org.example.homeworkcreateendpoint.dto.CustomerResponse;
import org.example.homeworkcreateendpoint.dto.UpdateCustomerRequest;
import org.example.homeworkcreateendpoint.mapper.CustomerMapper;
import org.example.homeworkcreateendpoint.repository.CustomerRepository;
import org.example.homeworkcreateendpoint.repository.CustomerSegmentRepository;
import org.example.homeworkcreateendpoint.repository.KYCRepository;
import org.example.homeworkcreateendpoint.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSegmentRepository customerSegmentRepository;
    private final KYCRepository kycRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }


    @Override
    public void deleteByPhoneNumber(String phoneNumber) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found"));

        customerRepository.delete(customer);
    }


    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found"));

        customerMapper.toCustomerPartially(updateCustomerRequest,
                customer);

        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
    }


    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        // validate email
        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exists!");
        }

        // Validate phone number
        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Phone number already exists!");
        }

        // Validation national card id
        if (kycRepository.existsByNationalCardId(createCustomerRequest.nationalCardId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "National card ID already exists!");
        }

        // Validation customer segment
        CustomerSegment customerSegment = customerSegmentRepository
                .findBySegment(createCustomerRequest.customerSegment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
                        "Customer segment not found!"));

        // Map data from DTO
        Customer customer = customerMapper.toCustomer(createCustomerRequest);

        // Prepare KYC of customer
        KYC kyc = new KYC();
        kyc.setNationalCardId(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);

        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerSegment(customerSegment);
        customer.setKyc(kyc);

        log.info("Customer before save: {}", customer.getId());

        customer = customerRepository.save(customer);

        log.info("Customer before save: {}", customer.getId());

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

}