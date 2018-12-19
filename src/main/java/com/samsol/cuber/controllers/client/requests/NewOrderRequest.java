package com.samsol.cuber.controllers.client.requests;

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
    private Long fromNodeId;
    @NotNull
    private Long toNodeId;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public Long getToNodeId() {
        return toNodeId;
    }
}
