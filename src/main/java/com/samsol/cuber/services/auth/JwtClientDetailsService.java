package com.samsol.cuber.services.auth;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.services.crud.ClientCRUDService;
import com.samsol.cuber.services.security.JwtClient;
import com.samsol.cuber.services.security.JwtClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtClientDetailsService implements UserDetailsService {

    private ClientCRUDService clientCRUDService;

    @Autowired
    public JwtClientDetailsService(ClientCRUDService clientCRUDService) {
        this.clientCRUDService = clientCRUDService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDto clientDto = clientCRUDService.getClientByUsername(username);

        if (clientDto == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtClientFactory.create(clientDto);
        }
    }
}
