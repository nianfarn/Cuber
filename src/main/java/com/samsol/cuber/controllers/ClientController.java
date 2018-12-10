package com.samsol.cuber.controllers;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.client.ClientService;
import com.samsol.cuber.services.client.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("order")
public class ClientController {

    private ClientService clientService;
    private Long id = 199L;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "client")
    public String init() {
        return "client";
    }

    @GetMapping(value = "createOrder")
    public String createOrderInitialPage(Model model) {
        model.addAttribute("deliveryOrder", new DeliveryOrderDto());
        return "createOrder";
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
