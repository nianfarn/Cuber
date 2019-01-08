package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;

public interface DeliveryOrderCrudService {
    void addDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    void updateDeliveryOrder(DeliveryOrderDto deliveryOrderDto);

    List<DeliveryOrderDto> getDeliveryOrdersByClientId(Long id);

    void removeDeliveryOrderById(long id);

    DeliveryOrderDto getDeliveryOrderById(long id);

    List<DeliveryOrderDto> getAllDeliveryOrders();

    DeliveryOrderDto getClientOrderById(long id);

    List<DeliveryOrderDto> getDeliveryOrdersByCourierId(Long id);
}
