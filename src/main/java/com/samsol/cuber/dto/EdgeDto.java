package com.samsol.cuber.dto;

import javax.validation.constraints.Positive;
import java.io.Serializable;

public class EdgeDto implements Serializable {

    private static final long serialVersionUID = 2869245403286295234L;
    private Long id;
    private Double length;
    private Long toNodeId;
    private Long fromNodeId;

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public EdgeDto setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
        return this;
    }

    public Long getToNodeId() {
        return toNodeId;
    }

    public EdgeDto setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public EdgeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public EdgeDto setLength(Double length) {
        this.length = length;
        return this;
    }

    public EdgeDto() {
    }
}
