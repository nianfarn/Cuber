package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.DeliveryOrder;
import com.samsol.cuber.repositories.DeliveryOrderRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.DeliveryOrderCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryOrderCRUDServiceImpl implements DeliveryOrderCRUDService {

    private DeliveryOrderRepository deliveryOrderRepository;
    private ConverterService<DeliveryOrder, DeliveryOrderDto> converter;

    @Autowired
    public DeliveryOrderCRUDServiceImpl(DeliveryOrderRepository deliveryOrderRepository, ConverterService<DeliveryOrder, DeliveryOrderDto> converter) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.converter = converter;
    }

    public void addDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        if (deliveryOrderRepository.existsById(deliveryOrderDto.getId()))
            System.out.println(("Delivery order already exist"));//fixme
        else
            deliveryOrderRepository.save(converter.convertToEntity(deliveryOrderDto));
    }

    public void updateDeliveryOrder(DeliveryOrderDto deliveryOrderDto) {
        if (!deliveryOrderRepository.existsById(deliveryOrderDto.getId()))
            System.out.println("This delivery order is not exist! \nCreated"); //fixme
        else
            deliveryOrderRepository.save(converter.convertToEntity(deliveryOrderDto));
    }

    @Override
    public List<DeliveryOrderDto> getDeliveryOrdersByClientId(Long id) {
        return converter.convertToDtoList(deliveryOrderRepository.findAllByClientId(id));
    }

    public void removeDeliveryOrderById(long id) {
        deliveryOrderRepository.deleteById(id);
    }

    public DeliveryOrderDto getDeliveryOrderById(long id) {
        return converter.convertToDto(deliveryOrderRepository.findById(id).orElse(null));
    }

    public List<DeliveryOrderDto> getAllDeliveryOrders() {
        return converter.convertToDtoList((List<DeliveryOrder>) deliveryOrderRepository.findAll());
    }

    public DeliveryOrderDto getClientOrderById(long id) {
        return converter.convertToDto(deliveryOrderRepository.findById(id).orElse(null));
    }

    @Override
    public List<DeliveryOrderDto> getDeliveryOrdersByCourierId(Long id) {
        return converter.convertToDtoList(deliveryOrderRepository.findAllByDeliveryCourierId(id));
    }
}
