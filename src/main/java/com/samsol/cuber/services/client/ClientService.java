package com.samsol.cuber.services.client;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;

public interface ClientService {

    ClientDto getClientById(Long id);

    void createNewOrder(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto getOrderById(long id);

    CourierDto calculateDeliveryCourier(DeliveryOrderDto deliveryOrderDto);

    DeliveryOrderDto generateNewOrder(DeliveryOrderDto deliveryOrderDto);

    void confirmNewOrderAdding(DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderDto> getClientOrdersByHisId(Long id);
}
