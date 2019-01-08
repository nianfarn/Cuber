package com.samsol.cuber.services.courier;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.services.web.JsonRepresentService;

import java.util.List;
import java.util.Map;

public interface CourierService {
    List<DeliveryOrderDto> getCourierOrdersByHisId(Long id);

    CourierDto getCourierById(long id);

    DeliveryOrderDto getOrderById(long id);

    void confirmArrival(long deliveryOrderId);
    void inTransit(long deliveryOrderId);

    void changeReadyStatus(CourierDto courier);
}
