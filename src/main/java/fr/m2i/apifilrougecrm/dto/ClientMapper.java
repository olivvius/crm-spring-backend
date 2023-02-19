package fr.m2i.apifilrougecrm.dto;

import fr.m2i.apifilrougecrm.entity.Client;

public class ClientMapper {

    public static ClientDTO buildClientDTO(Client client) {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setCompanyName(client.getCompanyName());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setZipCode(client.getZipCode());
        clientDTO.setCity(client.getCity());
        clientDTO.setCountry(client.getCountry());

        switch (client.getState()){
            case 0:
                clientDTO.setState("INACTIVE");
                break;
            case 1:
                clientDTO.setState("ACTIVE");
                break;
            default:
                clientDTO.setState("");
        }

        return clientDTO;
    }

    public static Client buildClient(ClientDTO clientDTO){

        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setCompanyName(clientDTO.getCompanyName());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        client.setZipCode(clientDTO.getZipCode());
        client.setCity(clientDTO.getCity());
        client.setCountry(clientDTO.getCountry());

        switch (clientDTO.getState()){
            case "INACTIVE":
                client.setState(0);
                break;
            case "ACTIVE":
                client.setState(1);
                break;
            default:
                client.setState(0);
        }

        return client;
    }

    public static Client copy(Client client, Client content){

        if (content.getCompanyName() != null) {
            client.setCompanyName(content.getCompanyName());
        }
        
        if (content.getFirstName() != null) {
            client.setFirstName(content.getFirstName());
        }
        
        if (content.getLastName() != null) {
            client.setLastName(content.getLastName());
        }
        
        if (content.getEmail() != null) {
            client.setEmail(content.getEmail());
        }
        
        if (content.getPhone() != null) {
            client.setPhone(content.getPhone());
        }
        
        if (content.getAddress() != null) {
            client.setAddress(content.getAddress());
        }
        
        if (content.getZipCode() != null) {
            client.setZipCode(content.getZipCode());
        }
        
        if (content.getCity() != null) {
            client.setCity(content.getCity());
        }
        
        if (content.getCountry() != null) {
            client.setCountry(content.getCountry());
        }
        
        if (content.getState() != -1) {
            client.setState(content.getState());
        }

        return client;
    }
}
