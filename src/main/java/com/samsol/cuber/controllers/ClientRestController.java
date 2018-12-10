package com.samsol.cuber.controllers;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.OrderStatus;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import com.samsol.cuber.services.logic.MapService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
public class ClientRestController {

    @Autowired
    private MapService mapService;

    @Autowired
    private DeliveryOrderCRUDService deliveryOrderCRUDService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/order/new/")
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid DeliveryOrderDto dto,
                                            BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>("Not valid data", HttpStatus.BAD_REQUEST);

        double length = mapService.calculateLength(dto.getFromNodeId(),
                dto.getToNodeId());
        dto.setPrice(mapService.calculatePrice(length));
        dto.setStatus(OrderStatus.WAITING_COURIER);
        dto.setCourierId(mapService.findClosestCourier(dto).getId());
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @PostMapping(value = "submitNewOrder")
    public String confirmOrder(@ModelAttribute @Valid DeliveryOrderDto deliveryOrder,
                               BindingResult bindingResult,
                               Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "createOrder";
        } else {
            deliveryOrder.setClientId(id);
            DeliveryOrderDto generatedOrder = clientService.generateNewOrder(deliveryOrder);

            model.addAttribute("order", generatedOrder);
        }
        return "orderData";
    }

    @PostMapping(value = "confirmNewOrderAdding")
    public String confirmAdding(@SessionAttribute("order") DeliveryOrderDto order) {
        clientService.confirmNewOrderAdding(order);
        return "client";
    }

    @GetMapping(value = "showClientOrders")
    public String showOrders(Model model) {
        model.addAttribute("clientsOrders", clientService.getClientOrdersByHisId(id));
        return "showClientOrders";
    }

}
