package com.samsol.cuber.dto;

import java.io.Serializable;

public class NodeDto implements Serializable {

    private static final long serialVersionUID = -2318719875701604768L;
    private Long id;
    private String address;
    private String description;


    interface New {
    }

    interface Exist {
    }

    interface UpdateAddress extends Exist {
    }

    interface UpdatedDescription extends Exist {
    }

    interface Update extends Exist {
    }

    interface Delete extends Exist {
    }

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

    public String getDescription() {
        return description;
    }

    public NodeDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public NodeDto() {
    }
}
