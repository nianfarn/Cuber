package com.samsol.cuber.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "UserDetails")
public class UserDetails implements Serializable{

    private static final long serialVersionUID = 6700091467608928377L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
    @Column
    private Boolean enabled;
    @Column
    private Date lastPasswordResetDate;
    @OneToOne(mappedBy = "clientDetails", fetch = FetchType.LAZY)
    private Client client;

    @OneToOne(mappedBy = "courierDetails", fetch = FetchType.LAZY)
    private Courier courier;

    public Long getId() {
        return id;
    }

    public UserDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDetails setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Authority getAuthority() {
        return authority;
    }

    public UserDetails setAuthority(Authority authority) {
        this.authority = authority;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UserDetails setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public UserDetails setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public UserDetails setClient(Client client) {
        this.client = client;
        return this;
    }

    public Courier getCourier() {
        return courier;
    }

    public UserDetails setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }
}