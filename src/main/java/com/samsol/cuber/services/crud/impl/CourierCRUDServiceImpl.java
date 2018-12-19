package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.repositories.CourierRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.CourierCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierCRUDServiceImpl implements CourierCRUDService {

    private CourierRepository courierRepository;
    private ConverterService<Courier, CourierDto> converter;

    @Autowired
    public CourierCRUDServiceImpl(CourierRepository courierRepository, ConverterService<Courier, CourierDto> converter) {
        this.courierRepository = courierRepository;
        this.converter = converter;
    }

    public void addCourier(CourierDto courierDto) {
        if (courierRepository.existsById(courierDto.getId()))
            System.out.println("This courier is already exist!\nUpdated");//todo translate into log
        else
            courierRepository.save(converter.convertToEntity(courierDto));
    }

    public void updateCourier(CourierDto courierDto) {
        if (!courierRepository.existsById(courierDto.getId()))
            System.out.println("This courier is not exist! \nCreated"); //todo translate into log
        else
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
