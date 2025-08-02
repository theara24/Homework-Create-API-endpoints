package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);
}