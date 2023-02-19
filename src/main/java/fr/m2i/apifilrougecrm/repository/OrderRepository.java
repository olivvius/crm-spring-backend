package fr.m2i.apifilrougecrm.repository;

import fr.m2i.apifilrougecrm.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
