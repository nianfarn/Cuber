package com.samsol.cuber.services.client;

import com.samsol.cuber.controllers.client.requests.NewOrderRequest;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;

public interface ClientService {

    ClientDto getClientById(Long id);

    DeliveryOrderDto getOrderById(long id);

    CourierDto calculateDeliveryCourier(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto generateNewOrder(NewOrderRequest deliveryOrderDto);

    void confirmNewOrderAdding(DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderDto> getClientOrdersByHisId(Long id);
}
