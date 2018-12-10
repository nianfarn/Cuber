package com.samsol.cuber.controllers;

import com.samsol.cuber.services.courier.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
public class CourierController {

    private CourierService courierService;
    private Long courierId = 2L;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping(value = "courier")
    public String init() {
        return "courier";
    }

    @GetMapping(value = "showCourierOrders")
    public String showCourierOrders(Model model) {
        model.addAttribute("couriersOrders", courierService.getCourierOrdersByHisId(courierId));
        return "showCourierOrders";
    }

    @GetMapping(value = "courier/orders/{id}")
    public String getCertainOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", courierService.getOrderById(id));
        return "orderData";
    }

    @GetMapping(value = "confirmArrival/{id}")
    public String confirmArrival(@PathVariable("id") long id, Model model) {
        courierService.confirmArrival(id);
        model.addAttribute("couriersOrders", courierService.getCourierOrdersByHisId(courierId));
        return "redirect:/showCourierOrders";
    }

    @GetMapping(value = "inTransit/{id}")
    public String inTransit(@PathVariable("id") long id, Model model) {
        courierService.inTransit(id);
        model.addAttribute("couriersOrders", courierService.getCourierOrdersByHisId(courierId));
        return "redirect:/showCourierOrders";
    }

    @GetMapping(value = "activate")
    public String activateCourier() {
        courierService.getCourierById(courierId).setReadyToGo(true);
        return "redirect:/courierStatus";
    }

    @GetMapping(value = "deactivate")
    public String deactivateCourier() {
        courierService.getCourierById(courierId).setReadyToGo(false);
        return "redirect:/courierStatus";
    }

    @GetMapping(value = "courierStatus")
    public String courierStatus(Model model) {
        model.addAttribute("status", courierService.getCourierById(courierId).getReadyToGo());
        return "courierStatus";
    }


}
