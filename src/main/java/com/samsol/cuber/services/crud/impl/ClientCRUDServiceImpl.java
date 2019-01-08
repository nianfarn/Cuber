package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.entities.Client;
import com.samsol.cuber.repositories.ClientRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.ClientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ClientCrudServiceImpl implements ClientCrudService {

    private ClientRepository clientRepository;
    private ConverterService<Client, ClientDto> converter;

    @Autowired
    public ClientCrudServiceImpl(ClientRepository clientRepository, ConverterService<Client, ClientDto> converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    public void addClient(@Valid ClientDto clientDto) {
            clientRepository.save(converter.convertToEntity(clientDto));
    }

    public void updateClient(@Valid ClientDto clientDto) {
        clientRepository.save(converter.convertToEntity(clientDto));
    }

    public void removeClientById(long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            throw new ResourceNotFoundException("Client not found");
        } else {
            return converter.convertToDto(client);
        }
    }

    public List<ClientDto> getAllClients() {
        return converter.convertToDtoList((List<Client>) clientRepository.findAll());
    }

    @Override
    public ClientDto getClientByUserDetailsId(Long id) {
        return converter.convertToDto(clientRepository.findByClientDetailsId(id));
    }


}
