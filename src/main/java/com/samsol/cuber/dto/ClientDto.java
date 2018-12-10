package com.samsol.cuber.dto;

import com.samsol.cuber.entities.AuthorityName;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;


public class ClientDto {

    private Long id;
    @Size(min = 6, max = 30)
    @MatchesPattern("[A-Za-z0-9]+")
    private String username;
    @MatchesPattern("[a-zA-Z]+")
    private String firstName;
    @MatchesPattern("[a-zA-Z]+")
    private String lastName;
    @Email
    private String email;
    @Min(value = 18)
    private Integer age;
    @Size(min = 6, max = 16)
    @MatchesPattern("[A-Za-z0-9]+")
    private String password;
    private AuthorityName authorityName;
    private boolean enabled;
    private Date lastPasswordResetDate;

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public ClientDto setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public ClientDto setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ClientDto setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ClientDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ClientDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientDto() {
    }
}
