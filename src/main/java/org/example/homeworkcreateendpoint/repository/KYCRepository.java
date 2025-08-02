package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository
        extends CrudRepository<KYC, Integer> {

    boolean existsByNationalCardId(String nationalCardId);

}