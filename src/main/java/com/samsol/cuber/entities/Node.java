package com.samsol.cuber.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Nodes")
public class Node implements Serializable {

    private static final long serialVersionUID = 2187278121664191941L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String address;

    @Column(unique = true)
    private String addressRu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromNode")
    private List<Edge> adjacencies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentNode")
    private List<Courier> couriersInThisNode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toNode")
    private List<Edge> aimedToThisNodeEdges;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromNodeLocation")
    private List<DeliveryOrder> listOrdersFromThisNode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toNodeLocation")
    private List<DeliveryOrder> listOrdersToThisNode;

    public long getId() {
        return id;
    }

    public Node setId(long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Node setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddressRu() {
        return addressRu;
    }

    public Node setAddressRu(String addressRu) {
        this.addressRu = addressRu;
        return this;
    }

    public List<Edge> getAdjacencies() {
        return adjacencies;
    }

    public Node setAdjacencies(List<Edge> adjacencies) {
        this.adjacencies = adjacencies;
        return this;
    }

    public List<Courier> getCouriersInThisNode() {
        return couriersInThisNode;
    }

    public Node setCouriersInThisNode(List<Courier> couriersInThisNode) {
        this.couriersInThisNode = couriersInThisNode;
        return this;
    }

    public List<Edge> getAimedToThisNodeEdges() {
        return aimedToThisNodeEdges;
    }

    public Node setAimedToThisNodeEdges(List<Edge> aimedToThisNodeEdges) {
        this.aimedToThisNodeEdges = aimedToThisNodeEdges;
        return this;
    }

    public List<DeliveryOrder> getListOrdersFromThisNode() {
        return listOrdersFromThisNode;
    }

    public Node setListOrdersFromThisNode(List<DeliveryOrder> listOrdersFromThisNode) {
        this.listOrdersFromThisNode = listOrdersFromThisNode;
        return this;
    }

    public List<DeliveryOrder> getListOrdersToThisNode() {
        return listOrdersToThisNode;
    }

    public Node setListOrdersToThisNode(List<DeliveryOrder> listOrdersToThisNode) {
        this.listOrdersToThisNode = listOrdersToThisNode;
        return this;
    }
}
