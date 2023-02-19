package fr.m2i.apifilrougecrm.controller;

import fr.m2i.apifilrougecrm.dto.ClientDTO;
import fr.m2i.apifilrougecrm.dto.ClientMapper;
import fr.m2i.apifilrougecrm.entity.Client;
import fr.m2i.apifilrougecrm.exception.NotFoundException;
import fr.m2i.apifilrougecrm.response.CrmResponseEntity;
import fr.m2i.apifilrougecrm.service.ClientService;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns the list of all clients", nickname = "Get all clients", response = ClientDTO.class)
    public ResponseEntity<List<ClientDTO>> getAll() {

        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientsDto = new ArrayList<>();

        for (Client client : clients) {
            ClientDTO clientDto = ClientMapper.buildClientDTO(client);
            clientsDto.add(clientDto);
        }

        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return a client", nickname = "Get a client by Id", response = ClientDTO.class)
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            Client client = clientService.findById(id);
            ClientDTO clientDTO = ClientMapper.buildClientDTO(client);
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Client not found", 404, "/v1/clients/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create client", nickname = "Create client", code = 201)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody ClientDTO clientDTO) {

        try {
            Client toCreate = ClientMapper.buildClient(clientDTO);
            Client created = clientService.create(toCreate);
            ClientDTO createdDto = ClientMapper.buildClientDTO(created);
            
            return ResponseEntity.created(URI.create("/v1/clients")).body(createdDto);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update client", nickname = "Update client", code = 204)
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ClientDTO clientDto) {

        clientDto.setId(id);
        Client client = ClientMapper.buildClient(clientDto);

        try {
            clientService.update(client);
            return ResponseEntity.ok(clientDto);
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Client not found", 404, "/v1/clients/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete client", nickname = "Delete client", code = 204)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Client not found", 404, "/v1/clients/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
