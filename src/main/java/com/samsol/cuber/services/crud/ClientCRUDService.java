package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.ClientDto;

import java.util.List;

public interface ClientCrudService {
    void addClient(ClientDto clientDto);

    void updateClient(ClientDto clientDto);

    void removeClientById(long id);

    ClientDto getClientById(Long id);

    List<ClientDto> getAllClients();

    ClientDto getClientByUserDetailsId(Long id);
}
