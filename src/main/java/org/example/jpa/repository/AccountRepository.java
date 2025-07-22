package org.example.jpa.repository;

import org.example.jpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<Account, Integer> {
    boolean existsByActNo(String actNo);
}