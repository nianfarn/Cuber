package com.samsol.cuber.controllers.requests;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NewOrderRequest implements Serializable {

    private static final long serialVersionUID = 737333640197695567L;

    @MatchesPattern("[A-Za-z]+")
    private String productName;
    @Max(800)
    private Integer weight;
    @Max(700)
    private Integer volume;
    @NotNull
    private String fromNode;
    @NotNull
    private String toNode;
    private Double price;

    public String getProductName() {
        return productName;
    }

    public NewOrderRequest setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public NewOrderRequest setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getVolume() {
        return volume;
    }

    public NewOrderRequest setVolume(Integer volume) {
        this.volume = volume;
        return this;
    }

    public String getFromNode() {
        return fromNode;
    }

    public NewOrderRequest setFromNode(String fromNode) {
        this.fromNode = fromNode;
        return this;
    }

    public String getToNode() {
        return toNode;
    }

    public NewOrderRequest setToNode(String toNode) {
        this.toNode = toNode;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public NewOrderRequest setPrice(Double price) {
        this.price = price;
        return this;
    }

    public NewOrderRequest() {
    }
}
