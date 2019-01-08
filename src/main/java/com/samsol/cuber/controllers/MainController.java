package com.samsol.cuber.controllers;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.UserDetails;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.courier.CourierService;
import com.samsol.cuber.services.crud.ClientCrudService;
import com.samsol.cuber.services.crud.CourierCrudService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import com.samsol.cuber.services.web.JsonRepresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class MainController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsCrudService userDetailsCrudService;
    @Autowired
    private CourierService courierService;
    @Autowired
    private CourierCrudService courierCrudService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientCrudService clientCrudService;
    @Autowired
    private JsonRepresentService jsonRepresentService;

    @GetMapping(value = "/frontWindow/ordersData")
    public ResponseEntity<?> getOrdersManagementData(HttpServletRequest request){
        String username = jwtTokenUtil.getUsernameByHttpServletRequest(request);
        String locale = request.getHeader("locale");

        UserDetailsDto ud = userDetailsCrudService.getUserDetailsByUsername(username);
        List<DeliveryOrderDto> orderList = new ArrayList<>();
        switch (ud.getAuthorityName()){
            case ROLE_COURIER:
                orderList = courierService.getCourierOrdersByHisId(courierCrudService.getCourierByUserDetailsId(ud.getId()).getId());
                break;
            case ROLE_CLIENT:
                orderList = clientService.getClientOrdersByHisId(clientCrudService.getClientByUserDetailsId(ud.getId()).getId());
                break;
        }
        List<Map<String,String>> jsonResponse = jsonRepresentService.generateJsonForOrders(orderList, locale);
        Map<String,String> roleMapElement = new HashMap<>();
        roleMapElement.put("role", ud.getAuthorityName().toString().substring(5));
        jsonResponse.add(roleMapElement);
        return ResponseEntity.ok().body(jsonResponse);
    }

    @GetMapping(value = "/dashboard")
    public ResponseEntity<?> openSpecificDashboard(HttpServletRequest request){
        String username = jwtTokenUtil.getUsernameByHttpServletRequest(request);
        switch (userDetailsCrudService.getUserDetailsByUsername(username).getAuthorityName()){
            case ROLE_CLIENT:
                return ResponseEntity.ok("client");
            case ROLE_COURIER:
                return ResponseEntity.ok("courier");
            case ROLE_ADMIN:
                return ResponseEntity.ok("admin");
        }
        return null;
    }
}
