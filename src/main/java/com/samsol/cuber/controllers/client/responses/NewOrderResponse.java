package com.samsol.cuber.controllers.client.responses;

import com.samsol.cuber.controllers.client.requests.NewOrderRequest;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewOrderResponse implements Serializable{

    private static final long serialVersionUID = -4376828275579993501L;
    @MatchesPattern("[A-Za-z]+")
    private String name;
    @Max(800)
    private Integer weight;
    @Max(700)
    private Integer volume;
    @NotNull
    private Long fromNodeId;
    @NotNull
    private Long toNodeId;
    @NotNull
    private Double price;

    public NewOrderResponse(NewOrderRequest request, Double price){
        this.name = request.getProductName();
        this.weight = request.getWeight();
        this.volume = request.getVolume();
        this.fromNodeId = request.getFromNodeId();
        this.toNodeId = request.getToNodeId();
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public NewOrderResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public NewOrderResponse setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getVolume() {
        return volume;
    }

    public NewOrderResponse setVolume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public NewOrderResponse setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
        return this;
    }

    public Long getToNodeId() {
        return toNodeId;
    }

    public NewOrderResponse setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public NewOrderResponse setPrice(Double price) {
        this.price = price;
        return this;
    }
}
