package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.services.security.JwtRegistrationRequest;

import javax.validation.Valid;
import java.util.List;

public interface ClientCRUDService {
    void addClient(ClientDto clientDto);

    void updateClient(ClientDto clientDto);

    void removeClientById(long id);

    ClientDto getClientById(Long id);

    List<ClientDto> getAllClients();

    ClientDto getClientByUserDetailsId(Long id);
}
