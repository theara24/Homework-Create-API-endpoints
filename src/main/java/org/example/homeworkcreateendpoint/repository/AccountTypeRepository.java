package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends
        JpaRepository<AccountType, Integer> {

    Optional<AccountType> findByType(String type);

}