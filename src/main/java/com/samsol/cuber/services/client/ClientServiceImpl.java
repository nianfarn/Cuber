package com.samsol.cuber.services.client;


import com.samsol.cuber.controllers.client.requests.NewOrderRequest;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.OrderStatus;
import com.samsol.cuber.services.crud.ClientCRUDService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import com.samsol.cuber.services.logic.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private MapService mapService;
    @Autowired
    private DeliveryOrderCRUDService deliveryOrderCRUDService;
    @Autowired
    private ClientCRUDService clientCrudService;

    public List<DeliveryOrderDto> getClientOrdersByHisId(Long id) {
        return deliveryOrderCRUDService.getDeliveryOrdersByClientId(id);
    }

    public CourierDto calculateDeliveryCourier(DeliveryOrderDto orderDto) {
        return mapService.findClosestCourier(orderDto);
    }

    @Override
    public DeliveryOrderDto generateNewOrder(NewOrderRequest request) {
        long fromId = request.getFromNodeId();
        long toId = request.getToNodeId();
        DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
        deliveryOrderDto.setId(0L);
        deliveryOrderDto.setProductName(request.getProductName());
        deliveryOrderDto.setWeight(request.getWeight());
        deliveryOrderDto.setVolume(request.getVolume());
        deliveryOrderDto.setPrice(calculatePrice(mapService.calculateLength(fromId, toId)));
        deliveryOrderDto.setStatus(OrderStatus.WAITING_FOR_COURIER);
        deliveryOrderDto.setFromNodeId(fromId);
        deliveryOrderDto.setToNodeId(toId);
        deliveryOrderDto.setCourierId(mapService.findClosestCourier(deliveryOrderDto).getId());
        return deliveryOrderDto;
    }

    public void confirmNewOrderAdding(DeliveryOrderDto deliveryOrderDto) {
        deliveryOrderCRUDService.addDeliveryOrder(deliveryOrderDto);
    }

    private double calculatePrice(Double length) {
        return Math.round(mapService.calculatePrice(length) * 100) / 100.;
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientCrudService.getClientById(id);
    }


    public DeliveryOrderDto getOrderById(long id) {
        return deliveryOrderCRUDService.getClientOrderById(id);
    }

}
