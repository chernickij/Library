package com.chernickij.bookservice.repository;

import com.chernickij.bookservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // TODO: Need to improve
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
