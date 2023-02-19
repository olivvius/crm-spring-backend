package fr.m2i.apifilrougecrm.service;
import fr.m2i.apifilrougecrm.dto.OrderDTO;
import fr.m2i.apifilrougecrm.dto.OrderMapper;
import fr.m2i.apifilrougecrm.entity.Order;
import fr.m2i.apifilrougecrm.exception.NotFoundException;
import fr.m2i.apifilrougecrm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Order update(OrderDTO content) {
        Order toUpdate = findById(content.getId());
        toUpdate = OrderMapper.copy(toUpdate, content);

        return orderRepository.save(toUpdate);
    }

    public void delete(Long id){
        Order toDelete = findById(id);
        orderRepository.delete(toDelete);
    }
}
