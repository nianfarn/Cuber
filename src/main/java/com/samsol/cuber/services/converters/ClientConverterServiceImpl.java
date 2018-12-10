package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.entities.Client;
import com.samsol.cuber.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientConverterServiceImpl implements ConverterService<Client, ClientDto> {

    private AuthorityRepository authorityRepository;

    @Autowired
    public ClientConverterServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public ClientDto convertToDto(final Client client) {
        if(client == null) return null;
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setUsername(client.getUsername());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setAge(client.getAge());
        clientDto.setPassword(client.getPassword());
        clientDto.setEnabled(client.getEnabled());
        clientDto.setAuthorityName(client.getAuthority().getName());
        clientDto.setLastPasswordResetDate(client.getLastPasswordResetDate());

        return clientDto;
    }

    @Override
    public Client convertToEntity(final ClientDto clientDto) {
        final Client client = new Client();
        client.setId(clientDto.getId());
        client.setAge(clientDto.getAge());
        client.setEmail(clientDto.getEmail());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setUsername(clientDto.getUsername());
        client.setPassword(clientDto.getPassword());
        client.setEnabled(clientDto.isEnabled());
        client.setAuthority(authorityRepository.findByName(clientDto.getAuthorityName()));
        client.setLastPasswordResetDate(clientDto.getLastPasswordResetDate());
        return client;
    }
}
