package com.laft17s.mscompclientperson.repository;

import com.laft17s.mscompclientperson.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByDni(String dni);
}
