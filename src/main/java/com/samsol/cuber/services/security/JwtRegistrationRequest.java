package com.samsol.cuber.services.security;

import com.samsol.cuber.entities.Authority;
import com.samsol.cuber.entities.AuthorityName;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class JwtRegistrationRequest implements Serializable {


    private static final long serialVersionUID = 7062218458314617034L;
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
    private AuthorityName type;

    public AuthorityName getType() {
        return type;
    }

    public JwtRegistrationRequest setType(AuthorityName type) {
        this.type = type;
        return this;
    }

    public JwtRegistrationRequest() {
    }



    public String getUsername() {
        return username;
    }

    public JwtRegistrationRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public JwtRegistrationRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public JwtRegistrationRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public JwtRegistrationRequest setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public JwtRegistrationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtRegistrationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
