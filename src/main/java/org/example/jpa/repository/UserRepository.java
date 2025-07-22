package org.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.jpa.domain.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
