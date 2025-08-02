package org.example.homeworkcreateendpoint;

import org.example.homeworkcreateendpoint.domain.Customer;
import org.example.homeworkcreateendpoint.domain.KYC;
import org.example.homeworkcreateendpoint.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeworkCreateEndpointApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkCreateEndpointApplication.class, args);
    }

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

//        KYC kyc = new KYC();
//        Customer customer = new Customer();
//
//        kyc.setNationalCardId("99990000");
//        kyc.setIsVerified(false);
//        kyc.setIsDeleted(false);
//        kyc.setCustomer(customer);
//
//        customer.setFullName("CHAN CHHAYA");
//        customer.setGender("M");
//        customer.setEmail("it.chhaya@gmail.com");
//        customer.setPhoneNumber("123456789");
//        customer.setKyc(kyc);
//        customer.setRemark("INSTRUCTOR");
//        customer.setIsDeleted(false);
//
//        customerRepository.save(customer);
    }
}