package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.entities.DeliveryOrder;
import com.samsol.cuber.repositories.DeliveryOrderRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.DeliveryOrderCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class DeliveryOrderCrudServiceImpl implements DeliveryOrderCrudService {

    private DeliveryOrderRepository deliveryOrderRepository;
    private ConverterService<DeliveryOrder, DeliveryOrderDto> converter;

    @Autowired
    public DeliveryOrderCrudServiceImpl(DeliveryOrderRepository deliveryOrderRepository, ConverterService<DeliveryOrder, DeliveryOrderDto> converter) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.converter = converter;
    }

    public void addDeliveryOrder(@Valid DeliveryOrderDto deliveryOrderDto) {
        deliveryOrderRepository.save(converter.convertToEntity(deliveryOrderDto));
    }

    public void updateDeliveryOrder(@Valid DeliveryOrderDto deliveryOrderDto) {
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
