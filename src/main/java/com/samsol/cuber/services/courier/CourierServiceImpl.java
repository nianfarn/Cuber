package com.samsol.cuber.services.courier;


import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.crud.CourierCRUDService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    private CourierCRUDService courierCRUDService;
    private DeliveryOrderCRUDService deliveryOrderCRUDService;

    @Autowired
    public CourierServiceImpl(CourierCRUDService courierCRUDService, DeliveryOrderCRUDService deliveryOrderCRUDService) {
        this.courierCRUDService = courierCRUDService;
        this.deliveryOrderCRUDService = deliveryOrderCRUDService;
    }

    public CourierDto getCourierById(long id) {
        return courierCRUDService.getCourierById(id);
    }

    public DeliveryOrderDto getOrderById(long id) {
        return deliveryOrderCRUDService.getDeliveryOrderById(id);
    }

    public void confirmArrival(long deliveryOrderId) {
        DeliveryOrderDto orderDto = deliveryOrderCRUDService.getDeliveryOrderById(deliveryOrderId);
        orderDto.setStatus("Arrived");
        deliveryOrderCRUDService.updateDeliveryOrder(orderDto);
        CourierDto courierDto = courierCRUDService.getCourierById(orderDto.getCourierId());
        courierDto.setReadyToGo(true);
        courierCRUDService.updateCourier(courierDto);

    }

    public List<DeliveryOrderDto> getCourierOrdersByHisId(Long id) {
        return deliveryOrderCRUDService.getDeliveryOrdersByCourierId(id);
    }

    public void inTransit(long deliveryOrderId) {
        DeliveryOrderDto orderDto = deliveryOrderCRUDService.getDeliveryOrderById(deliveryOrderId);
        orderDto.setStatus("In transit");
        deliveryOrderCRUDService.updateDeliveryOrder(orderDto);
    }
}
