package com.samsol.cuber.controllers.requests;

import java.io.Serializable;

public class CalculatePriceRequest implements Serializable {

    private static final long serialVersionUID = 8420024206297456231L;
    private String fromNode;
    private String toNode;

    public String getFromNode() {
        return fromNode;
    }

    public CalculatePriceRequest setFromNode(String fromNode) {
        this.fromNode = fromNode;
        return this;
    }

    public String getToNode() {
        return toNode;
    }

    public CalculatePriceRequest setToNode(String toNode) {
        this.toNode = toNode;
        return this;
    }

    public CalculatePriceRequest() {
    }
}
