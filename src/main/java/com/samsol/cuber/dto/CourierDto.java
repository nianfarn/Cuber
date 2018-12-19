package com.samsol.cuber.dto;

import com.samsol.cuber.entities.UserDetails;

import java.io.Serializable;

public class CourierDto implements Serializable {

    private static final long serialVersionUID = 2455636214476304229L;
    private Long id;
    private Long userDetailsId;

    private Boolean readyToGo;
    private Long currentNodeId;

    public Long getId() {
        return id;
    }

    public CourierDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public CourierDto setUserDetailsId(Long userDetailsId) {
        this.userDetailsId = userDetailsId;
        return this;
    }

    public Boolean getReadyToGo() {
        return readyToGo;
    }

    public CourierDto setReadyToGo(Boolean readyToGo) {
        this.readyToGo = readyToGo;
        return this;
    }

    public Long getCurrentNodeId() {
        return currentNodeId;
    }

    public CourierDto setCurrentNodeId(Long currentNodeId) {
        this.currentNodeId = currentNodeId;
        return this;
    }

    public CourierDto() {
    }
}
