package fr.m2i.apifilrougecrm.controller;

import fr.m2i.apifilrougecrm.dto.OrderDTO;
import fr.m2i.apifilrougecrm.dto.OrderMapper;
import fr.m2i.apifilrougecrm.entity.Order;
import fr.m2i.apifilrougecrm.exception.NotFoundException;
import fr.m2i.apifilrougecrm.response.CrmResponseEntity;
import fr.m2i.apifilrougecrm.service.OrderService;
import fr.m2i.apifilrougecrm.service.ClientService;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;
    
    @Autowired
    public OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns the list of all orders", nickname = "Get all orders", response = OrderDTO.class)
    public ResponseEntity<List<OrderDTO>> getAll() {

        List<Order> orders = orderService.findAll();
        List<OrderDTO> ordersDTO = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO orderDTO = OrderMapper.buildOrderDTO(order, clientService);
            ordersDTO.add(orderDTO);
        }

        return ResponseEntity.ok(ordersDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return an order", nickname = "Get an order by Id", response = OrderDTO.class)
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {

        try {
            Order order = orderService.findById(id);
            OrderDTO orderDTO = OrderMapper.buildOrderDTO(order, clientService);
            return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Order not found", 404, "/v1/orders/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create order", nickname = "Create order", code = 201)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody OrderDTO orderDTO) {

        try {
            Order order = OrderMapper.buildOrder(orderDTO);
            Order created = orderService.create(order);
            OrderDTO createdDto = OrderMapper.buildOrderDTO(created, clientService);
            return ResponseEntity.created(URI.create("/v1/orders")).body(createdDto);
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Client not found", 404, "/v1/orders/", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update order", nickname = "Update order", code = 204)
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody OrderDTO orderDto) {

        try {
            orderDto.setId(id);
            Order create = orderService.update(orderDto);
            return ResponseEntity.ok(orderDto);
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Order not found", 404, "/v1/orders/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete order", nickname = "Delete order", code = 204)
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        try {
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return CrmResponseEntity.build("Order not found", 404, "/v1/orders/" + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CrmResponseEntity.build(e.getMessage(), 500, "/v1/orders/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
