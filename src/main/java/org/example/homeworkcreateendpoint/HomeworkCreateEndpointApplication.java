package org.example.homeworkcreateendpoint;

import org.example.homeworkcreateendpoint.domain.AccountType;
import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.repository.AccountTypeRepository;
import org.example.homeworkcreateendpoint.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeworkCreateEndpointApplication implements CommandLineRunner {

    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(HomeworkCreateEndpointApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed AccountType
        AccountType savingsType = new AccountType();
        savingsType.setTypeName("SAVINGS");
        savingsType.setDescription("Savings account for personal use");
        savingsType.setIsActive(true);
        accountTypeRepository.save(savingsType);

        // Seed Customer
        Customer customer = new Customer();
        customer.setFullName("CHIM THEARA");
        customer.setGender("Female");
        customer.setEmail("chimtheara@gmail.com");
        customer.setPhoneNumber("012345678");
        customer.setRemarks("STUDENT");
        customer.setIsDeleted(false);
        customerRepository.save(customer);
    }
}