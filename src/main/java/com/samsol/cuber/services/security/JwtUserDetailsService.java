package com.samsol.cuber.services.security;

import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsCrudService userDetailsCrudService;

    @Autowired
    private JwtUserFactory jwtUserFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsDto userDetailsDto = userDetailsCrudService.getUserDetailsByUsername(username);

        if (userDetailsDto == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return jwtUserFactory.create(userDetailsDto);
        }
    }
}
