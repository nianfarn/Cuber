package com.samsol.cuber.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AUTHORITY")
public class Authority implements Serializable{

    private static final long serialVersionUID = -7660043734683733823L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private int id;

    @Column(name = "NAME", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
    private List<UserDetails> userDetails;

    public int getId() {
        return id;
    }

    public Authority setId(int id) {
        this.id = id;
        return this;
    }

    public AuthorityName getName() {
        return name;
    }

    public Authority setName(AuthorityName name) {
        this.name = name;
        return this;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public Authority setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
        return this;
    }
}