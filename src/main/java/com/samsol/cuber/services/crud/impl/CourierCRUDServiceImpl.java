package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.repositories.CourierRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.CourierCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class CourierCrudServiceImpl implements CourierCrudService {

    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private ConverterService<Courier, CourierDto> converter;

    public void addCourier(@Valid CourierDto courierDto){
        courierRepository.save(converter.convertToEntity(courierDto));
    }

    public void updateCourier(@Valid CourierDto courierDto) {
        courierRepository.save(converter.convertToEntity(courierDto));
    }

    public void removeCourierById(long id) {
        courierRepository.deleteById(id);
    }

    public CourierDto getCourierById(long id) {
        return converter.convertToDto(courierRepository.findById(id).orElse(null));
    }

    public List<CourierDto> getAllCouriers() {
        return converter.convertToDtoList((List<Courier>) courierRepository.findAll());
    }


    public List<CourierDto> getReadyToGoCouriers() {
        return converter.convertToDtoList(courierRepository.findAllByReadyToGo(true));
    }

    @Override
    public CourierDto getCourierByUserDetailsId(Long id) {
        return converter.convertToDto(courierRepository.findByCourierDetailsId(id));
    }
}
