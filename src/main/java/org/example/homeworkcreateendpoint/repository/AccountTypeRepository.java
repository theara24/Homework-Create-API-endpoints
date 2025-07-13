package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}