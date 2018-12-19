package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.entities.Client;
import com.samsol.cuber.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientConverterServiceImpl implements ConverterService<Client, ClientDto> {

    @Autowired
    private UserDetailsRepository repository;

    public ClientDto convertToDto(final Client client) {
        if(client == null) return null;
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setUserDetailsId(client.getClientDetails().getId());

        return clientDto;
    }

    @Override
    public Client convertToEntity(final ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setClientDetails(repository.findById(clientDto.getId()).orElse(null));
        return client;
    }
}
