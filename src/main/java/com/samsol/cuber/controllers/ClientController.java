package com.samsol.cuber.controllers;

import com.samsol.cuber.controllers.requests.CalculatePriceRequest;
import com.samsol.cuber.controllers.requests.NewOrderRequest;
import com.samsol.cuber.controllers.responses.SimpleMessageResponse;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.crud.ClientCrudService;
import com.samsol.cuber.services.crud.DeliveryOrderCrudService;
import com.samsol.cuber.services.crud.NodeCrudService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.logic.MapService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import com.samsol.cuber.services.web.JsonRepresentService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@PreAuthorize("hasRole('CLIENT')")
@RequestMapping("/client/")
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
    private ClientCrudService clientCRUDService;
    @Autowired
    private MapService mapService;
    @Autowired
    private DeliveryOrderCrudService deliveryOrderCRUDService;
    @Autowired
    private NodeCrudService nodeCrudService;
    @Autowired
    private JsonRepresentService jsonRepresentService;

    @GetMapping("nodes")
    public ResponseEntity getLocaleAddressList(HttpServletRequest request){
        String locale = request.getHeader("locale");
        List<NodeDto> list = nodeCrudService.getAllNodes();
        List<String> localeAddressList = new ArrayList<>();
        switch (locale){
            case "en":
                for (NodeDto node:list) {
                    localeAddressList.add(node.getAddress());
                }
                break;
            case "ru":
                for (NodeDto node:list) {
                    localeAddressList.add(node.getAddressRu());
                }
                break;
        }
        // removing logic node (starting node)
        localeAddressList.remove(0);
        return ResponseEntity.ok().body(localeAddressList);
    }

    @PostMapping(value = "/orders/new/calculatePrice")
    public ResponseEntity<?> calculatePrice(@RequestBody CalculatePriceRequest calculatePriceRequest, HttpServletRequest request) {
        Objects.requireNonNull(calculatePriceRequest.getFromNode());
        Objects.requireNonNull(calculatePriceRequest.getToNode());

        String locale = request.getHeader("locale");
        Long fromNodeId = nodeCrudService.getNodeDtoByHisLocaleAddress(calculatePriceRequest.getFromNode(),locale).getId();
        Long toNodeId = nodeCrudService.getNodeDtoByHisLocaleAddress(calculatePriceRequest.getToNode(),locale).getId();

        double length = mapService.calculateLength(fromNodeId, toNodeId);
        double price = mapService.calculatePrice(length);
        return ResponseEntity.ok(price);
    }

    @PostMapping(value = "/orders/new/submit")
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid NewOrderRequest newOrderRequest,
                                             BindingResult result,
                                             HttpServletRequest request) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body("Not valid data");

        String locale = request.getHeader("locale");
        String clientUsername = jwtTokenUtil.getUsernameByHttpServletRequest(request);

        DeliveryOrderDto deliveryOrderDto = clientService.generateNewOrder(newOrderRequest, locale);
        UserDetailsDto userDetailsDto = userDetailsCrudService.getUserDetailsByUsername(clientUsername);
        ClientDto clientDto = clientCRUDService.getClientByUserDetailsId(userDetailsDto.getId());
        deliveryOrderDto.setClientId(clientDto.getId());
        deliveryOrderCRUDService.addDeliveryOrder(deliveryOrderDto);
        return ResponseEntity.ok().body(new SimpleMessageResponse("Successfully added!"));
    }

    @GetMapping(value = "orders/showAll")
    public ResponseEntity<?> showOrders(HttpServletRequest request) {
        String clientUsername = jwtTokenUtil.getUsernameByHttpServletRequest(request);
        String locale = request.getHeader("locale");

        List<DeliveryOrderDto> orderList = getClientOrdersByHisUsername(clientUsername);
        List<Map<String,String>> jsonResponse = jsonRepresentService.generateJsonForOrders(orderList, locale);
        return ResponseEntity.ok().body(jsonResponse);
    }

    private List<DeliveryOrderDto> getClientOrdersByHisUsername(String clientUsername){
        Long clientUserDetailsId = userDetailsCrudService.getUserDetailsByUsername(clientUsername).getId();
        ClientDto client = clientCRUDService.getClientByUserDetailsId(clientUserDetailsId);
        return clientService.getClientOrdersByHisId(client.getId());
    }
}
