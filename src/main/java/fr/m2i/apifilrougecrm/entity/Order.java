package fr.m2i.apifilrougecrm.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import fr.m2i.apifilrougecrm.serializer.ClientSerializer;
import fr.m2i.apifilrougecrm.serializer.OrderSerializer;
import javax.persistence.*;

@JsonSerialize(using = OrderSerializer.class)
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_presta")
    private String typePresta;

    @Column(name="designation")
    private String designation;

    @Column(name="nb_days")
    private int nbDays;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "state")
    private int state; // CANCELLED:0 OPTION:1 CONFIRMED:2

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    public Order(String typePresta, String designation, int nbDays, float unitPrice, int state, Client client) {
        this.typePresta = typePresta;
        this.designation = designation;
        this.nbDays = nbDays;
        this.unitPrice = unitPrice;
        this.state = state;
        this.client = client;
    }

    public Order() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypePresta() {
        return typePresta;
    }

    public void setTypePresta(String typePresta) {
        this.typePresta = typePresta;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNbDays() {
        return nbDays;
    }

    public void setNbDays(int nbDays) {
        this.nbDays = nbDays;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
