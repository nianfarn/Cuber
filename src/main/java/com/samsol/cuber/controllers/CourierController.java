package com.samsol.cuber.controllers;

import com.samsol.cuber.controllers.responses.SimpleMessageResponse;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.services.courier.CourierService;
import com.samsol.cuber.services.crud.CourierCrudService;
import com.samsol.cuber.services.crud.DeliveryOrderCrudService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import com.samsol.cuber.services.web.JsonRepresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasRole('COURIER')")
@RequestMapping("/courier")
public class CourierController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private CourierCrudService courierCRUDService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsCrudService userDetailsCrudService;
    @Autowired
    private DeliveryOrderCrudService deliveryOrderCRUDService;
    @Autowired
    private CourierService courierService;
    @Autowired
    private JsonRepresentService jsonRepresentService;

    @PostMapping(value = "status/node/change")
    public ResponseEntity<?> changeNode(@RequestBody Map requestBodyMap, HttpServletRequest httpRequest) {
        CourierDto courier = getCourierDtoByRequest(httpRequest);
        courier.setCurrentNodeId(Long.parseLong((String) requestBodyMap.get("currentNodeId")));
        courierCRUDService.updateCourier(courier);
        return ResponseEntity.ok().body(new SimpleMessageResponse("Courier status was successfully updated!"));
    }

    @GetMapping(value = "status/refresh")
    public ResponseEntity<?> refreshStatus(HttpServletRequest httpRequest) {
        CourierDto courier = getCourierDtoByRequest(httpRequest);
        return ResponseEntity.ok().body(courier);
    }

    @GetMapping(value = "status/ready")
    public ResponseEntity<?> changeReadyStatus(HttpServletRequest httpRequest) {
        CourierDto courier = getCourierDtoByRequest(httpRequest);
        courierService.changeReadyStatus(courier);
        courierCRUDService.updateCourier(courier);
        return ResponseEntity.ok().body(courier);
    }

    @GetMapping(value = "orders/showAll")
    public ResponseEntity<?> showCourierOrders(HttpServletRequest request) {
        String courierUsername = getUsernameByHttpServletRequest(request);
        String locale = request.getHeader("locale");

        List<DeliveryOrderDto> orderList = getCourierOrdersByHisUsername(courierUsername);
        List<Map<String,String>> jsonResponse = jsonRepresentService.generateJsonForOrders(orderList, locale);
        return ResponseEntity.ok().body(jsonResponse);
    }

    private String getUsernameByHttpServletRequest(HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    private List<DeliveryOrderDto> getCourierOrdersByHisUsername(String clientUsername){
        Long clientUserDetailsId = userDetailsCrudService.getUserDetailsByUsername(clientUsername).getId();
        CourierDto courier = courierCRUDService.getCourierByUserDetailsId(clientUserDetailsId);
        return courierService.getCourierOrdersByHisId(courier.getId());
    }

    private CourierDto getCourierDtoByRequest(HttpServletRequest request){
        String courierUsername = getUsernameByHttpServletRequest(request);
        Long userDetailsId = userDetailsCrudService.getUserDetailsByUsername(courierUsername).getId();
        return courierCRUDService.getCourierByUserDetailsId(userDetailsId);
    }
}
