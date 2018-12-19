package com.samsol.cuber.dto;

import com.samsol.cuber.entities.AuthorityName;

import java.io.Serializable;

public class AuthorityDto implements Serializable {

    private static final long serialVersionUID = 5863970648884860859L;
    private int id;
    private AuthorityName authorityName;

    public int getId() {
        return id;
    }

    public AuthorityDto setId(int id) {
        this.id = id;
        return this;
    }

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public AuthorityDto setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public AuthorityDto() {
    }
}
