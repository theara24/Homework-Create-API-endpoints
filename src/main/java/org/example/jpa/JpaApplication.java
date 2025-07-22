package org.example.jpa;


import lombok.RequiredArgsConstructor;
import org.example.jpa.domain.Customer;
import org.example.jpa.domain.KYC;
import org.example.jpa.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        // KYC kyc = new KYC();
        // Customer customer = new Customer();

        // kyc.setNationalCardId("99990000");
        // kyc.setIsVerified(false);
        // kyc.setIsDeleted(false);
        // kyc.setCustomer(customer);

        // customer.setFullName("CHIM THEARA");
        // customer.setGender("F");
        // customer.setEmail("it.theara@gmail.com");
        // customer.setPhoneNumber("123456789");
        // customer.setKyc(kyc);
        // customer.setRemark("STUDENT");
        // customer.setIsDeleted(false);

        // customerRepository.save(customer);
    }
}
