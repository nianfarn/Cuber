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
@Table(name = "Couriers")
public class Courier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private int age;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "CURRENTNODE_ID")
    private Node currentNode;

    @Column(name = "READY")
    private Boolean readyToGo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deliveryCourier")
    private List<DeliveryOrder> orders;

    @ManyToOne
    @JoinColumn(name = "Authority_ID")
    private Authority courierAuthority;

    @Column
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Courier setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public Boolean getReadyToGo() {
        return readyToGo;
    }

    public Courier setReadyToGo(Boolean readyToGo) {
        this.readyToGo = readyToGo;
        return this;
    }

    public long getId() {
        return id;
    }

    public Courier setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Courier setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Courier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Courier setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Courier setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Courier setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Courier setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
        return this;
    }

    public List<DeliveryOrder> getOrders() {
        return orders;
    }

    public Courier setOrders(List<DeliveryOrder> orders) {
        this.orders = orders;
        return this;
    }

    public Authority getCourierAuthority() {
        return courierAuthority;
    }

    public Courier setCourierAuthority(Authority courierAuthority) {
        this.courierAuthority = courierAuthority;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Courier setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Courier() {
    }
}
