package fr.m2i.apifilrougecrm.service;

import fr.m2i.apifilrougecrm.dto.ClientMapper;
import fr.m2i.apifilrougecrm.entity.Client;
import fr.m2i.apifilrougecrm.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import fr.m2i.apifilrougecrm.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void update(Client content){

        Client toUpdate = findById(content.getId());
        toUpdate = ClientMapper.copy(toUpdate, content);

        clientRepository.save(toUpdate);
    }

    public void delete(Long id) {
        Client toDelete = findById(id);
        clientRepository.delete(toDelete);
    }

}
