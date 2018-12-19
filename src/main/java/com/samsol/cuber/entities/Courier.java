package com.samsol.cuber.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Couriers")
public class Courier implements Serializable {
    private static final long serialVersionUID = -3572653257578421432L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @OneToOne
    @JoinColumn(name = "UserDetails_id")
    private UserDetails courierDetails;

    @ManyToOne
    @JoinColumn(name = "CURRENTNODE_ID")
    private Node currentNode;

    @Column(name = "READY")
    private Boolean readyToGo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryCourier")
    private List<DeliveryOrder> orders;

    public UserDetails getCourierDetails() {
        return courierDetails;
    }

    public Courier setCourierDetails(UserDetails courierDetails) {
        this.courierDetails = courierDetails;
        return this;
    }

    public long getId() {
        return id;
    }

    public Courier setId(long id) {
        this.id = id;
        return this;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Courier setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public Boolean getReadyToGo() {
        return readyToGo;
    }

    public Courier setReadyToGo(Boolean readyToGo) {
        this.readyToGo = readyToGo;
        return this;
    }

    public List<DeliveryOrder> getOrders() {
        return orders;
    }

    public Courier setOrders(List<DeliveryOrder> orders) {
        this.orders = orders;
        return this;
    }
}
