package com.samsol.cuber.services.client;

import com.samsol.cuber.controllers.requests.NewOrderRequest;
import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;
import java.util.Map;

public interface ClientService {

    ClientDto getClientById(Long id);

    DeliveryOrderDto getOrderById(long id);

    CourierDto calculateDeliveryCourier(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto generateNewOrder(NewOrderRequest deliveryOrderDto, String locale);

    void confirmNewOrderAdding(DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderDto> getClientOrdersByHisId(Long id);
}
