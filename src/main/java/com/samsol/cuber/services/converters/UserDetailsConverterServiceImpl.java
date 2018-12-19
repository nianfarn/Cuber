package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.UserDetails;
import com.samsol.cuber.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsConverterServiceImpl implements ConverterService<UserDetails,UserDetailsDto> {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public UserDetailsDto convertToDto(UserDetails userDetails) {
        if(userDetails == null) return null;
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(userDetails.getId());
        userDetailsDto.setUsername(userDetails.getUsername());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setEmail(userDetails.getEmail());
        userDetailsDto.setAge(userDetails.getAge());
        userDetailsDto.setPassword(userDetails.getPassword());
        userDetailsDto.setEnabled(userDetails.getEnabled());
        userDetailsDto.setAuthorityName(userDetails.getAuthority().getName());
        userDetailsDto.setLastPasswordResetDate(userDetails.getLastPasswordResetDate());
        return userDetailsDto;
    }

    @Override
    public UserDetails convertToEntity(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDetailsDto.getId());
        userDetails.setUsername(userDetailsDto.getUsername());
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setEmail(userDetailsDto.getEmail());
        userDetails.setAge(userDetailsDto.getAge());
        userDetails.setPassword(userDetailsDto.getPassword());
        userDetails.setEnabled(userDetailsDto.getEnabled());
        userDetails.setAuthority(authorityRepository.findByName(userDetailsDto.getAuthorityName()));
        userDetails.setLastPasswordResetDate(userDetailsDto.getLastPasswordResetDate());
        return userDetails;
    }
}
