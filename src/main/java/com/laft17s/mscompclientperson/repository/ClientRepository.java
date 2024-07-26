package com.laft17s.mscompclientperson.repository;

import com.laft17s.mscompclientperson.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPersonDni(String dni);
}
