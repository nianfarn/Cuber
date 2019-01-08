package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.CourierDto;

import java.util.List;

public interface CourierCrudService {
    void addCourier(CourierDto courierDto);

    void updateCourier(CourierDto courierDto);

    void removeCourierById(long id);

    CourierDto getCourierById(long id);

    List<CourierDto> getAllCouriers();

    List<CourierDto> getReadyToGoCouriers();

    CourierDto getCourierByUserDetailsId(Long id);
}
