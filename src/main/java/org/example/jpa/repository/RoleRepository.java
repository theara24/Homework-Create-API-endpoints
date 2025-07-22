package org.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.jpa.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
