package com.laft17s.mscompclientperson.dao;

import com.laft17s.mscompclientperson.dto.ClientDTO;
import com.laft17s.mscompclientperson.dto.PersonDTO;
import com.laft17s.mscompclientperson.entities.Person;
import com.laft17s.mscompclientperson.mappers.ClientMapper;
import com.laft17s.mscompclientperson.mappers.PersonMapper;
import com.laft17s.mscompclientperson.repository.ClientRepository;
import com.laft17s.mscompclientperson.repository.PersonRepository;
import com.laft17s.mscompclientperson.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClientPersonServiceDAO {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClientService clientService;

    public List<PersonDTO> filterPerson(PersonDTO request) {
        Person data = personMapper.toPerson(request);
        return personMapper.toListPersonDTO(personRepository.findAll());
    }

    public PersonDTO findPersonByDni(String dni) {
        Person data = personRepository.findByDni(dni);

        if (data != null) {
            return personMapper.toPersonDTO(data);
        } else {
            throw new EntityNotFoundException("Persona con DNI " + dni + " no encontrada.");
        }

    }

    public ClientDTO findByDni(String dni) {
        return clientMapper.toClientDTO(clientService.getClientByDni(dni));
    }

    public List<PersonDTO> listPersons() {
        return personMapper.toListPersonDTO(personRepository.findAll());
    }

    public List<ClientDTO> listClients() {
        return clientMapper.toListClientDTO(clientRepository.findAll());
    }

    public void savePerson (PersonDTO request) {
        personRepository.save(personMapper.toPerson(request));
    }

    public void saveClient (ClientDTO request) {
        clientRepository.save(clientMapper.toClient(request));
    }

    public void updatePerson (String dni, PersonDTO request) {
        Person data = personRepository.findByDni(dni);

        if (data != null) {
            data.setName(request.getName());
            data.setGender(request.getGender());
            data.setAge(request.getAge());
            data.setPhoneNumber(request.getPhoneNumber());

            personRepository.save(data);
        } else {
            throw new EntityNotFoundException("Persona con DNI " + request.getDni() + " no encontrada.");
        }
    }

    public void deletePerson (String dni) {
        Person data = personRepository.findByDni(dni);
        personRepository.delete(data);
    }

    public void deleteClientByDni (String dni) {
        ClientDTO data = findByDni(dni);

        clientRepository.delete(clientMapper.toClient(data));
    }

}
