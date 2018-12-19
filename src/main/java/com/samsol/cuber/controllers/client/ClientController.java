package com.samsol.cuber.controllers.client;

import com.samsol.cuber.controllers.SimpleMessageResponse;
import com.samsol.cuber.controllers.client.requests.CalculatePriceRequest;
import com.samsol.cuber.controllers.client.requests.NewOrderRequest;
import com.samsol.cuber.controllers.client.responses.NewOrderResponse;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.crud.ClientCRUDService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.logic.MapService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@PreAuthorize("hasRole('CLIENT')")
@RequestMapping("/dashboard/client")
public class ClientController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private ClientService clientService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsCrudService userDetailsCrudService;
    @Autowired
    private ClientCRUDService clientCRUDService;
    @Autowired
    private MapService mapService;
    @Autowired
    private DeliveryOrderCRUDService deliveryOrderCRUDService;

    @PostMapping(value = "/orders/new/calculatePrice")
    public ResponseEntity<?> calculatePrice(@RequestBody CalculatePriceRequest request) {
        Objects.requireNonNull(request.getFromNodeId());
        Objects.requireNonNull(request.getToNodeId());

        double length = mapService.calculateLength(request.getFromNodeId(),
                request.getToNodeId());
        double price = mapService.calculatePrice(length);
        return ResponseEntity.ok(price);
    }

    @PostMapping(value = "/orders/new/submit")
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid NewOrderRequest request,
                                             BindingResult result,
                                             HttpServletRequest httpRequest) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body("Not valid data");
        DeliveryOrderDto deliveryOrderDto = clientService.generateNewOrder(request);
        String clientUsername = getUsernameByHttpServletRequest(httpRequest);
        UserDetailsDto userDetailsDto = userDetailsCrudService.getUserDetailsByUsername(clientUsername);
        ClientDto clientDto = clientCRUDService.getClientByUserDetailsId(userDetailsDto.getId());
        deliveryOrderDto.setClientId(clientDto.getId());
        deliveryOrderCRUDService.addDeliveryOrder(deliveryOrderDto);
        return ResponseEntity.ok().body(new SimpleMessageResponse("Successfully added!"));
    }

    @GetMapping(value = "orders/showAll")
    public ResponseEntity<?> showOrders(HttpServletRequest httpRequest) {
        String clientUsername = getUsernameByHttpServletRequest(httpRequest);
        Long clientUserDetailsId = userDetailsCrudService.getUserDetailsByUsername(clientUsername).getId();
        ClientDto client = clientCRUDService.getClientByUserDetailsId(clientUserDetailsId);
        List<DeliveryOrderDto> orderList = clientService.getClientOrdersByHisId(client.getId());
        return ResponseEntity.ok().body(orderList);
    }

    private String getUsernameByHttpServletRequest(HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
