package com.samsol.cuber.controllers.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsol.cuber.controllers.ClientController;
import com.samsol.cuber.controllers.requests.NewOrderRequest;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.crud.ClientCrudService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {


    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private ClientController controller;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //fixme
    @Test
    @WithMockUser
    public void successfulNewOrderCreation() throws Exception {
        NewOrderRequest newOrderRequest = new NewOrderRequest();
        newOrderRequest.setProductName("Wool");
        newOrderRequest.setWeight(50);
        newOrderRequest.setVolume(300);
        newOrderRequest.setFromNode("Dolgobrodskaya street");
        newOrderRequest.setToNode("Karla Libknehta street");
        newOrderRequest.setPrice(16.32);


        mvc.perform(post("/dashboard/client/orders/new/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newOrderRequest)))
                .andExpect(status().is2xxSuccessful());
    }
}