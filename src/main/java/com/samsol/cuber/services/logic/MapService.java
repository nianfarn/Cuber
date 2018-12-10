package com.samsol.cuber.services.logic;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;

public interface MapService {
    CourierDto findClosestCourier(DeliveryOrderDto orderDto);

    Double calculateLength(Long fromId, Long toId);

    double calculatePrice(Double length);
}
