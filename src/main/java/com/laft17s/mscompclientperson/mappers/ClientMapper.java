package com.laft17s.mscompclientperson.mappers;

import com.laft17s.mscompclientperson.dto.ClientDTO;
import com.laft17s.mscompclientperson.entities.Client;
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
public interface ClientMapper {

    Client toClient(ClientDTO data);

    ClientDTO toClientDTO(Client data);

    List<ClientDTO> toListClientDTO(List<Client> list);

}
