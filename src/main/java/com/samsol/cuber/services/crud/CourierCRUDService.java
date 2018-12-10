package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.services.security.JwtRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourierCRUDService {
    void addCourier(CourierDto courierDto);

    void updateCourier(CourierDto courierDto);

    void removeCourierById(long id);

    CourierDto getCourierById(long id);

    List<CourierDto> getAllCouriers();

    CourierDto getCourierByUsername(String nickname);

    List<CourierDto> getReadyToGoCouriers();

    CourierDto generateNewCourier(JwtRegistrationRequest registrationRequest);
}
