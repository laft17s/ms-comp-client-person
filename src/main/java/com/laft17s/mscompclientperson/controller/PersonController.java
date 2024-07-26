package com.laft17s.mscompclientperson.controller;

import com.laft17s.mscompclientperson.dao.ClientPersonServiceDAO;
import com.laft17s.mscompclientperson.dto.GenericResponseDTO;
import com.laft17s.mscompclientperson.dto.PersonDTO;
import com.laft17s.mscompclientperson.entities.Person;
import com.laft17s.mscompclientperson.repository.PersonRepository;
import com.laft17s.mscompclientperson.utils.ClientPersonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    //
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ClientPersonServiceDAO clientPersonServiceDAO;

    @GetMapping
    public List<Person> getAll () {
        return personRepository.findAll();
    }

    @PostMapping
    public GenericResponseDTO create (@RequestBody PersonDTO person) {
        try {

            clientPersonServiceDAO.savePerson(person);

            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.CREATED.value(),
                    GenericResponseDTO.Status.OK,
                    null,
                    "Se creó a la persona correctamente."
            );

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al crear a la persona: " + e.getMessage(),
                    e
            );
        }
    }

}
