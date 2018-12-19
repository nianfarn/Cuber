package com.samsol.cuber.dto;

import java.io.Serializable;


public class ClientDto implements Serializable{

    private static final long serialVersionUID = -7094683552122990770L;

    private Long id;
    private Long userDetailsId;

    public Long getId() {
        return id;
    }

    public ClientDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public ClientDto setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
        return this;
    }

    public ClientDto() {
    }
}
