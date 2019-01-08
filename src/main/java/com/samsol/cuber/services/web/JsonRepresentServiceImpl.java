package com.samsol.cuber.services.web;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.OrderStatus;
import com.samsol.cuber.services.crud.NodeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonRepresentServiceImpl implements JsonRepresentService {
    @Autowired
    private NodeCrudService nodeCrudService;

    @Override
    public List<Map<String, String>> generateJsonForOrders(List<DeliveryOrderDto> orderList, String locale) {
        List<Map<String, String>> retList = new ArrayList<>();
        for (DeliveryOrderDto order:orderList) {
            Map<String, String> jsonMap = new LinkedHashMap<>();
            switch (locale){
                case "en":
                    jsonMap.put("Id", order.getId().toString());
                    jsonMap.put("Product Name", order.getProductName());
                    jsonMap.put("From", nodeCrudService.getNodeById(order.getFromNodeId()).getAddress());
                    jsonMap.put("To", nodeCrudService.getNodeById(order.getToNodeId()).getAddress());
                    jsonMap.put("Weight", order.getWeight().toString());
                    jsonMap.put("Volume", order.getVolume().toString());
                    jsonMap.put("Price", order.getPrice().toString());
                    jsonMap.put("Client Id", order.getClientId().toString());
                    jsonMap.put("Courier Id", order.getCourierId().toString());
                    if (order.getStatus().equals(OrderStatus.DELIVERED)) {
                        jsonMap.put("Status", "Delivered");
                    }
                    else if (order.getStatus().equals(OrderStatus.IN_TRANSIT)) {
                        jsonMap.put("Status", "In transit");
                    }
                    else if (order.getStatus().equals(OrderStatus.WAITING_FOR_COURIER)) {
                        jsonMap.put("Status", "Waiting for courier");
                    }

                    break;

                case "ru":
                    jsonMap.put("Идентификатор", order.getId().toString());
                    jsonMap.put("Имя товара", order.getProductName());
                    jsonMap.put("Откуда", nodeCrudService.getNodeById(order.getFromNodeId()).getAddressRu());
                    jsonMap.put("Куда", nodeCrudService.getNodeById(order.getToNodeId()).getAddressRu());
                    jsonMap.put("Вес", order.getWeight().toString());
                    jsonMap.put("Объем", order.getVolume().toString());
                    jsonMap.put("Цена", order.getPrice().toString());
                    jsonMap.put("Код клиента", order.getClientId().toString());
                    jsonMap.put("Код курьера", order.getCourierId().toString());
                    if (order.getStatus().equals(OrderStatus.DELIVERED)) {
                        jsonMap.put("Статус", "Доставлен");
                    }
                    else if (order.getStatus().equals(OrderStatus.IN_TRANSIT)) {
                        jsonMap.put("Статус", "Доставляется");
                    }
                    else if (order.getStatus().equals(OrderStatus.WAITING_FOR_COURIER)) {
                        jsonMap.put("Статус", "Ждет курьера");
                    }

                    break;
            }
            retList.add(jsonMap);
        }
        return retList;
    }
}
