package com.samsol.cuber.controllers.client.requests;

import java.io.Serializable;

public class CalculatePriceRequest implements Serializable {

    private static final long serialVersionUID = 8420024206297456231L;
    private Long fromNodeId;
    private Long toNodeId;

    public Long getFromNodeId() {
        return fromNodeId;
    }

    public CalculatePriceRequest setFromNodeId(Long fromNodeId) {
        this.fromNodeId = fromNodeId;
        return this;
    }

    public Long getToNodeId() {
        return toNodeId;
    }

    public CalculatePriceRequest setToNodeId(Long toNodeId) {
        this.toNodeId = toNodeId;
        return this;
    }

    public CalculatePriceRequest() {
    }
}
