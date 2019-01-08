package com.samsol.cuber.services.courier;


import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.OrderStatus;
import com.samsol.cuber.services.crud.CourierCrudService;
import com.samsol.cuber.services.crud.DeliveryOrderCrudService;
import com.samsol.cuber.services.crud.NodeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private CourierCrudService courierCRUDService;
    @Autowired
    private DeliveryOrderCrudService deliveryOrderCRUDService;
    @Autowired
    private NodeCrudService nodeCrudService;

    public CourierDto getCourierById(long id) {
        return courierCRUDService.getCourierById(id);
    }

    public DeliveryOrderDto getOrderById(long id) {
        return deliveryOrderCRUDService.getDeliveryOrderById(id);
    }

    public void confirmArrival(long deliveryOrderId) {
        DeliveryOrderDto orderDto = deliveryOrderCRUDService.getDeliveryOrderById(deliveryOrderId);
        orderDto.setStatus(OrderStatus.DELIVERED);
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
        orderDto.setStatus(OrderStatus.IN_TRANSIT);
        deliveryOrderCRUDService.updateDeliveryOrder(orderDto);
    }

    @Override
    public void changeReadyStatus(CourierDto courier) {
        if(courier.getReadyToGo()) {
            courier.setReadyToGo(false);
        } else {
            courier.setReadyToGo(true);
        }
    }
}
