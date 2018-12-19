package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.DeliveryOrder;
import com.samsol.cuber.repositories.ClientRepository;
import com.samsol.cuber.repositories.CourierRepository;
import com.samsol.cuber.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderConverterServiceImpl implements ConverterService<DeliveryOrder, DeliveryOrderDto> {

    private CourierRepository courierRepository;
    private ClientRepository clientRepository;
    private NodeRepository nodeRepository;

    @Autowired
    public DeliveryOrderConverterServiceImpl(CourierRepository courierRepository, ClientRepository clientRepository, NodeRepository nodeRepository) {
        this.courierRepository = courierRepository;
        this.clientRepository = clientRepository;
        this.nodeRepository = nodeRepository;
    }

    @Override
    public DeliveryOrderDto convertToDto(DeliveryOrder deliveryOrder) {
        DeliveryOrderDto deliveryOrderDto = new DeliveryOrderDto();
        deliveryOrderDto.setId(deliveryOrder.getId());
        deliveryOrderDto.setProductName(deliveryOrder.getProductName());
        deliveryOrderDto.setWeight(deliveryOrder.getWeight());
        deliveryOrderDto.setVolume(deliveryOrder.getVolume());
        deliveryOrderDto.setPrice(deliveryOrder.getPrice());
        deliveryOrderDto.setStatus(deliveryOrder.getStatus());
        deliveryOrderDto.setCourierId(deliveryOrder.getDeliveryCourier().getId());
        deliveryOrderDto.setClientId(deliveryOrder.getClient().getId());
        deliveryOrderDto.setFromNodeId(deliveryOrder.getFromNodeLocation().getId());
        deliveryOrderDto.setToNodeId(deliveryOrder.getToNodeLocation().getId());
        return deliveryOrderDto;
    }

    @Override
    public DeliveryOrder convertToEntity(DeliveryOrderDto deliveryOrderDto) {
        final DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(deliveryOrderDto.getId());
        deliveryOrder.setProductName(deliveryOrderDto.getProductName());
        deliveryOrder.setWeight(deliveryOrderDto.getWeight());
        deliveryOrder.setVolume(deliveryOrderDto.getVolume());
        deliveryOrder.setPrice(deliveryOrderDto.getPrice());
        deliveryOrder.setStatus(deliveryOrderDto.getStatus());
        deliveryOrder.setDeliveryCourier(courierRepository.findById(deliveryOrderDto.getCourierId()).orElse(null));
        deliveryOrder.setClient(clientRepository.findById(deliveryOrderDto.getClientId()).orElse(null));
        deliveryOrder.setFromNodeLocation(nodeRepository.findById(deliveryOrderDto.getFromNodeId()).orElse(null));
        deliveryOrder.setToNodeLocation(nodeRepository.findById(deliveryOrderDto.getToNodeId()).orElse(null));
        return deliveryOrder;
    }
}

