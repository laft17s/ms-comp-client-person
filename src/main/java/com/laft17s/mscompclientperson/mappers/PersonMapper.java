package com.laft17s.mscompclientperson.mappers;

import com.laft17s.mscompclientperson.dto.PersonDTO;
import com.laft17s.mscompclientperson.entities.Person;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PersonMapper {

    Person toPerson(PersonDTO data);

    PersonDTO toPersonDTO(Person data);

    List<PersonDTO> toListPersonDTO(List<Person> list);

}
