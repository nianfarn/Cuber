package com.samsol.cuber.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class DeliveryOrderDto {

    private Long id;
    private String productName;
    @Max(value = 800, groups = {New.class, Update.class})
    private Integer weight;
    @Null(groups = {Delete.class})
    @Max(value = 800, groups = {New.class, Update.class})
    private Integer volume;
    private Double price;
    private String status;
    private Long courierId;
    private Long fromNodeLocationId;
    private Long toNodeLocationId;
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

    public Long getFromNodeLocationId() {
        return fromNodeLocationId;
    }

    public DeliveryOrderDto setFromNodeLocationId(Long fromNodeLocationId) {
        this.fromNodeLocationId = fromNodeLocationId;
        return this;
    }

    public Long getToNodeLocationId() {
        return toNodeLocationId;
    }

    public DeliveryOrderDto setToNodeLocationId(Long toNodeLocationId) {
        this.toNodeLocationId = toNodeLocationId;
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

    public String getStatus() {
        return status;
    }

    public DeliveryOrderDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public DeliveryOrderDto() {
    }
}
