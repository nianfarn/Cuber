package com.samsol.cuber.dto;

import com.samsol.cuber.entities.OrderStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class DeliveryOrderDto implements Serializable {

    private static final long serialVersionUID = 3620440187779669367L;
    private Long id;
    @Size(min = 2, max = 30)
    private String productName;
    @Max(value = 800)
    private Integer weight;
    @Max(value = 800)
    private Integer volume;
    private Double price;
    private OrderStatus status;
    private Long courierId;
    private Long fromNodeId;
    private Long toNodeId;
    private Long clientId;

    interface New {
    }

    interface Exist {
    }

    interface Update extends Exist {
    }

    interface Delete extends Exist {
    }


    public Long getClientId() {
        return clientId;
    }

    public DeliveryOrderDto setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public DeliveryOrderDto setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
        return this;
    }

    public Long getToNodeId() {
        return toNodeId;
    }

    public DeliveryOrderDto setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getCourierId() {
        return courierId;
    }

    public DeliveryOrderDto setCourierId(Long courierId) {
        this.courierId = courierId;
        return this;
    }

    public DeliveryOrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public DeliveryOrderDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public DeliveryOrderDto setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getVolume() {
        return volume;
    }

    public DeliveryOrderDto setVolume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public DeliveryOrderDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public DeliveryOrderDto() {
    }
}
