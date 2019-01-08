package com.samsol.cuber.services.web;

import com.samsol.cuber.dto.DeliveryOrderDto;

import java.util.List;
import java.util.Map;

public interface JsonRepresentService {
    List<Map<String, String>> generateJsonForOrders(List<DeliveryOrderDto> orderList, String locale);
}
