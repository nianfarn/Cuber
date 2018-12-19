package com.samsol.cuber.controllers;

import java.io.Serializable;

public class SimpleMessageResponse implements Serializable {

    private static final long serialVersionUID = -5333618044071516610L;
    private String message;

    public SimpleMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public SimpleMessageResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
