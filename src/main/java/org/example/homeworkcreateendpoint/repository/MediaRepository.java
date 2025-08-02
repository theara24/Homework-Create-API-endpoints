package org.example.homeworkcreateendpoint.repository;

import org.example.homeworkcreateendpoint.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Optional<Media> findByNameAndExtension(String name, String extension);

}