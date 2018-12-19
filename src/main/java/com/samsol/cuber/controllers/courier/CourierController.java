package com.samsol.cuber.controllers.courier;

import com.samsol.cuber.controllers.SimpleMessageResponse;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.courier.CourierService;
import com.samsol.cuber.services.crud.CourierCRUDService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import jdk.nashorn.internal.parser.JSONParser;
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
@RequestMapping("/dashboard/courier")
public class CourierController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private CourierCRUDService courierCRUDService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsCrudService userDetailsCrudService;
    @Autowired
    private DeliveryOrderCRUDService deliveryOrderCRUDService;
    @Autowired
    private CourierService courierService;

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
    public ResponseEntity<?> showCourierOrders(HttpServletRequest httpRequest) {
        CourierDto courier = getCourierDtoByRequest(httpRequest);
        if (courier==null) return ResponseEntity.badRequest().body(new SimpleMessageResponse("Unexpected error"));
        List<DeliveryOrderDto> orderList = courierService.getCourierOrdersByCourierId(courier.getId());
        return ResponseEntity.ok().body(orderList);
    }

    private String getUsernameByHttpServletRequest(HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    private CourierDto getCourierDtoByRequest(HttpServletRequest request){
        String courierUsername = getUsernameByHttpServletRequest(request);
        Long userDetailsId = userDetailsCrudService.getUserDetailsByUsername(courierUsername).getId();
        return courierCRUDService.getCourierByUserDetailsId(userDetailsId);
    }
}
