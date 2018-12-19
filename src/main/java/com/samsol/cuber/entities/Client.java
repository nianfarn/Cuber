package com.samsol.cuber.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client implements Serializable {
    private static final long serialVersionUID = 8698408512280140603L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "UserDetails_id")
    private UserDetails clientDetails;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<DeliveryOrder> orders;


    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDetails getClientDetails() {
        return clientDetails;
    }

    public Client setClientDetails(UserDetails clientDetails) {
        this.clientDetails = clientDetails;
        return this;
    }


    public List<DeliveryOrder> getOrders() {
        return orders;
    }

    public Client setOrders(List<DeliveryOrder> orders) {
        this.orders = orders;
        return this;
    }
}
