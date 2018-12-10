package com.samsol.cuber.services.client;


import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.crud.ClientCRUDService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import com.samsol.cuber.services.logic.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private MapService mapService;
    private DeliveryOrderCRUDService deliveryOrderCRUDService;
    private ClientCRUDService clientCrudService;

    @Autowired
    public ClientServiceImpl(MapService mapService, DeliveryOrderCRUDService deliveryOrderCRUDService, ClientCRUDService clientCrudService) {
        this.mapService = mapService;
        this.deliveryOrderCRUDService = deliveryOrderCRUDService;
        this.clientCrudService = clientCrudService;
    }

    public List<DeliveryOrderDto> getClientOrdersByHisId(Long id) {
        return deliveryOrderCRUDService.getDeliveryOrdersByClientId(id);
    }

    public CourierDto calculateDeliveryCourier(DeliveryOrderDto orderDto) {
        return mapService.findClosestCourier(orderDto);
    }

    public DeliveryOrderDto generateNewOrder(DeliveryOrderDto deliveryOrderDto) {
        long fromId = deliveryOrderDto.getFromNodeId();
        long toId = deliveryOrderDto.getToNodeId();
        deliveryOrderDto.setPrice(calculatePrice(mapService.calculateLength(fromId, toId)));
        deliveryOrderDto.setStatus("Waiting for courier");
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

    public void createNewOrder(DeliveryOrderDto deliveryOrderDto) {
        try {
            deliveryOrderCRUDService.addDeliveryOrder(deliveryOrderDto);//todo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DeliveryOrderDto getOrderById(long id) {
        return deliveryOrderCRUDService.getClientOrderById(id);
    }

}
