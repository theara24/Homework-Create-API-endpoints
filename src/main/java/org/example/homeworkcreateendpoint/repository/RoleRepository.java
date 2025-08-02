package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}