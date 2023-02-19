package fr.m2i.apifilrougecrm;

import fr.m2i.apifilrougecrm.entity.Client;
import fr.m2i.apifilrougecrm.entity.Order;
import fr.m2i.apifilrougecrm.service.ClientService;
import fr.m2i.apifilrougecrm.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiFilRougeCrmApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApiFilRougeCrmApplication.class, args);

        ClientService customerService = ctx.getBean(ClientService.class);
        OrderService orderService = ctx.getBean(OrderService.class);
        //creation des clients
        Client c1 = new Client("m2i", "Térieur", "Alex", "alex.térieur@m2i.fr", "0473429397", "13 rue du 14 juillet", "69004", "Lyon", "France", 1);
        Client c2 = new Client("m3i", "Térieur", "Alain", "alain.térieur@m2i.fr", "0473429397", "14 rue du 13 juillet", "69001", "Lyon", "France", 1);
        Client c3 = new Client("m4i", "Tim", "Alex", "alex.tim@m2i.fr", "0473429397", "13 rue du 14 juillet", "69001", "Lyon", "France", 0);
        //m2i.getPrestation().add(presta);
        customerService.create(c1);
        customerService.create(c2);
        customerService.create(c3);
        Order o1 = new Order("formation", "très cool", 3, 1200, 1, c1);
        Order o2 = new Order("cours", "pas cool", 4, 1600, 2, c2);
        Order o3 = new Order("formation", " cool", 5, 200, 0, c3);
        orderService.create(o1);
        orderService.create(o2);
        orderService.create(o3);

    }

}
