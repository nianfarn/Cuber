package com.samsol.cuber.services.courier;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;

public interface CourierService {
    List<DeliveryOrderDto> getCourierOrdersByCourierId(Long id);

    CourierDto getCourierById(long id);

    DeliveryOrderDto getOrderById(long id);

    void confirmArrival(long deliveryOrderId);
    void inTransit(long deliveryOrderId);

    void changeReadyStatus(CourierDto courier);
}
