package org.example.jpa.repository;


import org.example.jpa.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository
        extends CrudRepository<KYC, Integer> {
}
