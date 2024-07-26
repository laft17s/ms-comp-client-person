package com.laft17s.mscompclientperson.controller;


import com.laft17s.mscompclientperson.dao.ClientPersonServiceDAO;
import com.laft17s.mscompclientperson.dto.ClientDTO;
import com.laft17s.mscompclientperson.dto.GenericResponseDTO;
import com.laft17s.mscompclientperson.dto.PersonDTO;
import com.laft17s.mscompclientperson.utils.ClientPersonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    //
    @Autowired
    private ClientPersonServiceDAO clientPersonServiceDAO;

    @GetMapping
    public GenericResponseDTO getAll () {
        try {
            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.OK.value(),
                    GenericResponseDTO.Status.OK,
                    clientPersonServiceDAO.listClients(),
                    "Se muestra el listado de los clientes registrados."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al obtener la lista de clientes: " + e.getMessage(),
                    e
            );
        }
    }

    @GetMapping("/dni/{dni}")
    public GenericResponseDTO getByDmi (@PathVariable String dni) {
        try {
            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.OK.value(),
                    GenericResponseDTO.Status.OK,
                    clientPersonServiceDAO.findByDni(dni),
                    "Se muestra el listado de los clientes registrados."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al obtener la lista de clientes: " + e.getMessage(),
                    e
            );
        }
    }

    @PostMapping
    public GenericResponseDTO create (@RequestBody ClientDTO client) {

        try {
            // Guardar cliente
            clientPersonServiceDAO.saveClient(client);

            // Devolver respuesta exitosa
            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.CREATED.value(),
                    GenericResponseDTO.Status.OK,
                    null,
                    "Se creó el cliente correctamente."
            );
        } catch (Exception e) {
            // Manejar excepciones y devolver un código de estado de error
            // Aquí puedes usar diferentes códigos de estado según el tipo de excepción
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al crear el cliente: " + e.getMessage(),
                    e
            );
        }

    }

    @PutMapping("/dni/{dni}")
    public GenericResponseDTO update(@PathVariable String dni, @RequestBody ClientDTO updatedClient) {
        try {
            // Buscar el cliente por DNI
            ClientDTO existingClient = clientPersonServiceDAO.findByDni(dni);

            if (existingClient == null) {
                return ClientPersonUtils.mapServiceResponse(
                        HttpStatus.NOT_FOUND.value(),
                        GenericResponseDTO.Status.ERROR,
                        null,
                        "Cliente no encontrado."
                );
            }

            // Actualizar los detalles del cliente
            clientPersonServiceDAO.updatePerson(dni, updatedClient.getPerson());
            PersonDTO updatedPerson = clientPersonServiceDAO.findPersonByDni(dni);

            existingClient.setPerson(updatedPerson);
            existingClient.setStatus(updatedClient.getStatus());

            // Guardar los cambios
            clientPersonServiceDAO.saveClient(existingClient);

            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.OK.value(),
                    GenericResponseDTO.Status.OK,
                    null,
                    "Cliente actualizado correctamente."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente: " + e.getMessage(),
                    e
            );
        }
    }

    @DeleteMapping("/dni/{dni}")
    public GenericResponseDTO delete(@PathVariable String dni) {
        try {
            // Buscar el cliente por DNI
            ClientDTO existingClient = clientPersonServiceDAO.findByDni(dni);

            if (existingClient == null) {
                return ClientPersonUtils.mapServiceResponse(
                        HttpStatus.NOT_FOUND.value(),
                        GenericResponseDTO.Status.ERROR,
                        null,
                        "Cliente no encontrado."
                );
            }

            // Eliminar el cliente
            clientPersonServiceDAO.deleteClientByDni(dni);
            clientPersonServiceDAO.deletePerson(dni);

            return ClientPersonUtils.mapServiceResponse(
                    HttpStatus.OK.value(),
                    GenericResponseDTO.Status.OK,
                    null,
                    "Cliente eliminado correctamente."
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al eliminar el cliente: " + e.getMessage(),
                    e
            );
        }
    }

}
