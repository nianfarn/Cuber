package com.samsol.cuber.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "DeliveryOrders")
public class DeliveryOrder implements Serializable {
    private static final long serialVersionUID = -3660020369507722627L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String productName;

    @Column
    private int weight;

    @Column
    private int volume;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "fromNodeLocation_ID")
    private Node fromNodeLocation;

    @ManyToOne
    @JoinColumn(name = "toNodeLocation_ID")
    private Node toNodeLocation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "CURRENTNODE_ID")
    private Node currentNode;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "COURIER_ID")
    private Courier deliveryCourier;

    public long getId() {
        return id;
    }

    public DeliveryOrder setId(long id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public DeliveryOrder setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public DeliveryOrder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public int getVolume() {
        return volume;
    }

    public DeliveryOrder setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public DeliveryOrder setPrice(double price) {
        this.price = price;
        return this;
    }

    public Node getFromNodeLocation() {
        return fromNodeLocation;
    }

    public DeliveryOrder setFromNodeLocation(Node fromNodeLocation) {
        this.fromNodeLocation = fromNodeLocation;
        return this;
    }

    public Node getToNodeLocation() {
        return toNodeLocation;
    }

    public DeliveryOrder setToNodeLocation(Node toNodeLocation) {
        this.toNodeLocation = toNodeLocation;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryOrder setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public DeliveryOrder setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public DeliveryOrder setClient(Client client) {
        this.client = client;
        return this;
    }

    public Courier getDeliveryCourier() {
        return deliveryCourier;
    }

    public DeliveryOrder setDeliveryCourier(Courier deliveryCourier) {
        this.deliveryCourier = deliveryCourier;
        return this;
    }
}
