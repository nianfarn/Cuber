package com.samsol.cuber.dto;

import com.samsol.cuber.entities.Authority;
import com.samsol.cuber.entities.AuthorityName;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserDetailsDto implements Serializable {

    private static final long serialVersionUID = -8451024127751891614L;
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
    private Boolean enabled;
    private Date lastPasswordResetDate;

    public Long getId() {
        return id;
    }

    public UserDetailsDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserDetailsDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailsDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public UserDetailsDto setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UserDetailsDto setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public UserDetailsDto setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
        return this;
    }

    public UserDetailsDto() {
    }
}
