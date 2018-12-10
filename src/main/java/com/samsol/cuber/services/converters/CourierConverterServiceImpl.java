package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.entities.Courier;
import com.samsol.cuber.repositories.AuthorityRepository;
import com.samsol.cuber.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierConverterServiceImpl implements ConverterService<Courier, CourierDto> {

    private NodeRepository nodeRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public CourierConverterServiceImpl(NodeRepository nodeRepository, AuthorityRepository authorityRepository) {
        this.nodeRepository = nodeRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public CourierDto convertToDto(final Courier courier) {
        CourierDto courierDto = new CourierDto();
        courierDto.setId(courier.getId());
        courierDto.setUsername(courier.getUsername());
        courierDto.setFirstName(courier.getFirstName());
        courierDto.setLastName(courier.getLastName());
        courierDto.setEmail(courier.getEmail());
        courierDto.setAge(courier.getAge());
        courierDto.setPassword(courier.getPassword());
        courierDto.setReadyToGo(courier.getReadyToGo());
        courierDto.setCurrentNodeId(courier.getCurrentNode().getId());
        courierDto.setEnabled(courier.getEnabled());
        courierDto.setAuthorityName(courier.getCourierAuthority().getName());
        return courierDto;
    }

    @Override
    public Courier convertToEntity(final CourierDto courierDto) {
        final Courier courier = new Courier();
        courier.setId(courierDto.getId());
        courier.setUsername(courierDto.getUsername());
        courier.setFirstName(courierDto.getFirstName());
        courier.setLastName(courierDto.getLastName());
        courier.setEmail(courierDto.getEmail());
        courier.setAge(courierDto.getAge());
        courier.setPassword(courierDto.getPassword());
        courier.setReadyToGo(courierDto.getReadyToGo());
        courier.setCurrentNode(nodeRepository.findById(courierDto.getCurrentNodeId()).orElse(null));
        courier.setEnabled(courierDto.isEnabled());
        courier.setCourierAuthority(authorityRepository.findByName(courierDto.getAuthorityName()));
        courier.setLastPasswordResetDate(courierDto.getLastPasswordResetDate());
        return courier;
    }
}
