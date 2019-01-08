package com.samsol.cuber.services.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsol.cuber.controllers.requests.JwtRegistrationRequest;
import com.samsol.cuber.entities.AuthorityName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserDetailsIntegrationTest {

    @Value("${jwt.route.registration.path}")
    private String regPath;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldAddNewUserWithValidData() throws Exception{
        JwtRegistrationRequest request = new JwtRegistrationRequest();
        request.setUsername("testUser");
        request.setAge(32);
        request.setEmail("test@test.te");
        request.setFirstName("test");
        request.setLastName("test");
        request.setPassword("testPass");
        request.setType(AuthorityName.ROLE_CLIENT);

        mvc.perform(post(regPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldNotAddNewUserWithInvalidUsername() throws Exception{
        JwtRegistrationRequest request = new JwtRegistrationRequest();
        request.setUsername("test");
        request.setAge(32);
        request.setEmail("test@test.te");
        request.setFirstName("test");
        request.setLastName("test");
        request.setPassword("testPass");
        request.setType(AuthorityName.ROLE_CLIENT);

        mvc.perform(post(regPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotAddingNewUserWithExistingUsername() throws Exception{
        JwtRegistrationRequest request = new JwtRegistrationRequest();
        request.setUsername("romanK");
        request.setAge(32);
        request.setEmail("test@test.te");
        request.setFirstName("test");
        request.setLastName("test");
        request.setPassword("testPass");
        request.setType(AuthorityName.ROLE_CLIENT);

        mvc.perform(post(regPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }

}