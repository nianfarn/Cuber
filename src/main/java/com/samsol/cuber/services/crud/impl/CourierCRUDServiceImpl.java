package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.entities.AuthorityName;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.repositories.CourierRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.CourierCRUDService;
import com.samsol.cuber.services.security.JwtRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourierCRUDServiceImpl implements CourierCRUDService {

    private CourierRepository courierRepository;
    private ConverterService<Courier, CourierDto> converter;

    @Value("${spring.security.BCryptEncoderStrength}")
    private int rounds;

    @Autowired
    public CourierCRUDServiceImpl(CourierRepository courierRepository, ConverterService<Courier, CourierDto> converter) {
        this.courierRepository = courierRepository;
        this.converter = converter;
    }

    public void addCourier(CourierDto courierDto) {
        if (courierRepository.existsById(courierDto.getId()))
            System.out.println("This courierDto is already exist!\nUpdated");//todo translate into log
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

    public CourierDto getCourierByUsername(String username) {
        return converter.convertToDto(courierRepository.findByUsername(username));
    }

    public List<CourierDto> getReadyToGoCouriers() {
        return converter.convertToDtoList(courierRepository.findAllByReadyToGo(true));
    }

    @Override
    public CourierDto generateNewCourier(JwtRegistrationRequest registrationRequest) {
        CourierDto courierDto = new CourierDto();
        courierDto.setUsername(registrationRequest.getUsername());
        courierDto.setFirstName(registrationRequest.getFirstName());
        courierDto.setLastName(registrationRequest.getLastName());
        courierDto.setEmail(registrationRequest.getEmail());
        courierDto.setAge(registrationRequest.getAge());
        courierDto.setPassword(BCrypt.hashpw(registrationRequest.getPassword(),BCrypt.gensalt(rounds)));
        courierDto.setAuthorityName(AuthorityName.ROLE_COURIER);
        courierDto.setEnabled(true);
        courierDto.setLastPasswordResetDate(new Date());
        return courierDto;
    }
}
