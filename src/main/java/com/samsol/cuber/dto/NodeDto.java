package com.samsol.cuber.dto;

import java.io.Serializable;

public class NodeDto implements Serializable {

    private static final long serialVersionUID = -2318719875701604768L;
    private Long id;
    private String address;
    private String addressRu;

    public Long getId() {
        return id;
    }

    public NodeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public NodeDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddressRu() {
        return addressRu;
    }

    public NodeDto setAddressRu(String addressRu) {
        this.addressRu = addressRu;
        return this;
    }

    public NodeDto() {
    }
}
