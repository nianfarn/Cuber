package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.repositories.NodeRepository;
import com.samsol.cuber.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierConverterServiceImpl implements ConverterService<Courier, CourierDto> {

    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public CourierDto convertToDto(final Courier courier) {
        if(courier == null) return null;
        CourierDto courierDto = new CourierDto();
        courierDto.setId(courier.getId());
        courierDto.setUserDetailsId(courier.getCourierDetails().getId());
        courierDto.setReadyToGo(courier.getReadyToGo());
        courierDto.setCurrentNodeId(courier.getCurrentNode().getId());
        return courierDto;
    }

    @Override
    public Courier convertToEntity(final CourierDto courierDto) {
        Courier courier = new Courier();
        courier.setId(courierDto.getId());
        courier.setCourierDetails(userDetailsRepository.findById(courierDto.getUserDetailsId()).orElse(null));
        courier.setReadyToGo(courierDto.getReadyToGo());
        courier.setCurrentNode(nodeRepository.findById(courierDto.getCurrentNodeId()).orElse(null));
        return courier;
    }
}
