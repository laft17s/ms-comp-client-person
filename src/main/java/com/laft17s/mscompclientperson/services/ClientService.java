package com.laft17s.mscompclientperson.services;

import com.laft17s.mscompclientperson.entities.Client;
import com.laft17s.mscompclientperson.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClientByDni(String dni) {
        return clientRepository.findByPersonDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con dni: " + dni));
    }
}
