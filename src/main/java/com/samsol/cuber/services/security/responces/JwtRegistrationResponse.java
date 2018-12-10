package com.samsol.cuber.services.security.responces;

import java.io.Serializable;

public class JwtRegistrationResponse implements Serializable {

    private static final long serialVersionUID = 125016658502483573L;

    private final String message;

    public JwtRegistrationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
