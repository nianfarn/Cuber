package com.samsol.cuber.controllers.responses;

import com.samsol.cuber.dto.DeliveryOrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ClientOrdersResponse implements Serializable {

    private static final long serialVersionUID = 2583166508472465073L;
    private Long id;
    private String productName;
    private Integer weight;
    private Integer volume;
    private String from;
    private String to;
    private Long courierId;

    public Long getCourierId() {
        return courierId;
    }

    public ClientOrdersResponse setCourierId(Long courierId) {
        this.courierId = courierId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ClientOrdersResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ClientOrdersResponse setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public ClientOrdersResponse setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getVolume() {
        return volume;
    }

    public ClientOrdersResponse setVolume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public ClientOrdersResponse setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public ClientOrdersResponse setTo(String to) {
        this.to = to;
        return this;
    }

    public ClientOrdersResponse() {
    }
}
