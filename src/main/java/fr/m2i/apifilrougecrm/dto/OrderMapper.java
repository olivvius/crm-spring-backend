package fr.m2i.apifilrougecrm.dto;

import fr.m2i.apifilrougecrm.entity.Client;
import fr.m2i.apifilrougecrm.entity.Order;
import fr.m2i.apifilrougecrm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderMapper {

    /*
    @Autowired
    private ClientService clientService;
     */
    public static OrderDTO buildOrderDTO(Order order, ClientService clientService) {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setTypePresta(order.getTypePresta());
        orderDTO.setDesignation(order.getDesignation());
        orderDTO.setNbDays(order.getNbDays());
        orderDTO.setUnitPrice(order.getUnitPrice());

        //Client client = order.getClient();
        Client client = clientService.findById(order.getClient().getId());

        ClientDTO clientDTO = ClientMapper.buildClientDTO(client);
        orderDTO.setClient(clientDTO);

        switch (order.getState()) {
            case 0:
                orderDTO.setState("CANCELED");
                break;
            case 1:
                orderDTO.setState("OPTION");
                break;
            case 2:
                orderDTO.setState("CONFIRMED");
                break;
            default:
                orderDTO.setState("");
        }

        return orderDTO;
    }

    public static Order buildOrder(OrderDTO orderDTO) {

        Order order = new Order();

        order.setId(orderDTO.getId());
        order.setTypePresta(orderDTO.getTypePresta());
        order.setDesignation(orderDTO.getDesignation());
        order.setNbDays(orderDTO.getNbDays());
        order.setUnitPrice(orderDTO.getUnitPrice());

        if (orderDTO.getClient() != null && orderDTO.getClient().getId() != null) {
            order.setClient(new Client(orderDTO.getClient().getId()));
        }

        if (orderDTO.getState() == null) {
            return order;
        }

        order.setState(stringtToState(orderDTO.getState()));

        return order;
    }

    public static Order copy(Order toUpdate, OrderDTO content) {

        if (content.getTypePresta() != null) {
            toUpdate.setTypePresta(content.getTypePresta());
        }

        if (content.getDesignation() != null) {
            toUpdate.setDesignation(content.getDesignation());
        }

        if (content.getNbDays() != 0) {
            toUpdate.setNbDays(content.getNbDays());
        }

        if (content.getUnitPrice() != 0) {
            toUpdate.setUnitPrice(content.getUnitPrice());
        }

        if (content.getClient() != null && content.getClient().getId() != null) {
            toUpdate.setClient(new Client(content.getClient().getId()));
        }

        if (content.getState() != null) {
            toUpdate.setState(stringtToState(content.getState()));
        }

        return toUpdate;
    }

    public static int stringtToState(String stateStr) {

        switch (stateStr) {
            case "CANCELED":
                return 0;

            case "OPTION":
                return 1;

            case "CONFIRMED":
                return 2;

            default:
                return 0;
        }
    }
}
