package com.samsol.cuber.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client implements Serializable {
    private static final long serialVersionUID = 8698408512280140603L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private Integer age;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<DeliveryOrder> orders;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @ManyToOne
    @JoinColumn(name = "Authority_ID")
    private Authority authority;

    public Long getId() {
        return id;
    }

    public Client setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Client setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Client setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public Authority getAuthority() {
        return authority;
    }

    public Client setAuthority(Authority authority) {
        this.authority = authority;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Client setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Client setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<DeliveryOrder> getOrders() {
        return orders;
    }

    public Client setOrders(List<DeliveryOrder> orders) {
        this.orders = orders;
        return this;
    }
}
