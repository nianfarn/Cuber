package com.samsol.cuber.dto;

import com.samsol.cuber.entities.AuthorityName;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class CourierDto implements Serializable {

    private static final long serialVersionUID = 2455636214476304229L;
    private Long id;
    @Size(min = 6, max = 30)
    private String username;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Positive
    @Min(value = 18)
    private Integer age;
    @Size(min = 6, max = 16)
    private String password;
    private Boolean readyToGo;
    private Long currentNodeId;
    private AuthorityName authorityName;
    private boolean enabled;
    private Date lastPasswordResetDate;

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public CourierDto setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CourierDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CourierDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CourierDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CourierDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CourierDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CourierDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CourierDto setPassword(String password) {
        this.password = password;
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

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public CourierDto setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public CourierDto setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public CourierDto() {
    }
}
