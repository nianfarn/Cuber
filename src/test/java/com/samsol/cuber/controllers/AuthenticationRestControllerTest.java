package com.samsol.cuber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.AuthorityName;
import com.samsol.cuber.services.security.JwtUserDetailsService;
import com.samsol.cuber.controllers.requests.JwtAuthenticationRequest;
import com.samsol.cuber.services.security.JwtUser;
import com.samsol.cuber.services.security.JwtUserFactory;
import com.samsol.cuber.services.security.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationRestControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void successfulAuthenticationWithAnonymousUser() throws Exception {

        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest("user", "password");

        mvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(jwtAuthenticationRequest)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    public void successfulRefreshTokenWithClientRole() throws Exception {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername("username");
        userDetailsDto.setAuthorityName(AuthorityName.ROLE_CLIENT);
        userDetailsDto.setEnabled(Boolean.TRUE);
        userDetailsDto.setLastPasswordResetDate(new Date(System.currentTimeMillis() + 1000 * 1000));

        JwtUser jwtUser = context.getBean(JwtUserFactory.class).create(userDetailsDto);

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(userDetailsDto.getUsername());

        when(jwtUserDetailsService.loadUserByUsername(eq(userDetailsDto.getUsername()))).thenReturn(jwtUser);

        when(jwtTokenUtil.canTokenBeRefreshed(any(), any())).thenReturn(true);

        mvc.perform(get("/refresh")
                .header("Authorization", "Bearer 5d1103e-b3e1-4ae9-b606-46c9c1bc915a"))
                .andExpect(status().is2xxSuccessful());
    }
}