package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.AuthorityName;
import com.samsol.cuber.entities.UserDetails;
import com.samsol.cuber.repositories.UserDetailsRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
public class UserDetailsCrudServiceImpl implements UserDetailsCrudService {

    @Value("${spring.security.BCryptEncoderStrength}")
    private int rounds;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private ConverterService<UserDetails, UserDetailsDto> converter;

    @Override
    public void addUserDetails(@Valid UserDetailsDto userDetailsDto) {
        userDetailsRepository.save(converter.convertToEntity(userDetailsDto));
    }

    @Override
    public void updateUserDetails(@Valid UserDetailsDto userDetailsDto) {
        userDetailsRepository.save(converter.convertToEntity(userDetailsDto));
    }

    @Override
    public void removeUserDetailsById(long id) {
        userDetailsRepository.deleteById(id);
    }

    @Override
    public UserDetailsDto getUserDetailsById(long id) {
        return converter.convertToDto(userDetailsRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserDetailsDto> getAllUserDetails() {
        return converter.convertToDtoList((List<UserDetails>) userDetailsRepository.findAll());
    }

    @Override
    public UserDetailsDto generateNewUserDetails(JwtRegistrationRequest registrationRequest) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername(registrationRequest.getUsername());
        userDetailsDto.setFirstName(registrationRequest.getFirstName());
        userDetailsDto.setLastName(registrationRequest.getLastName());
        userDetailsDto.setEmail(registrationRequest.getEmail());
        userDetailsDto.setAge(registrationRequest.getAge());
        userDetailsDto.setPassword(BCrypt.hashpw(registrationRequest.getPassword(),BCrypt.gensalt(rounds)));
        userDetailsDto.setAuthorityName(registrationRequest.getType());
        userDetailsDto.setEnabled(true);
        userDetailsDto.setLastPasswordResetDate(new Date());
        return userDetailsDto;
    }

    @Override
    public UserDetailsDto getUserDetailsByUsername(String username) {
        return converter.convertToDto(userDetailsRepository.findByUsername(username));
    }

    @Override
    public UserDetailsDto getUserDetailsByEmail(String email) {
        return converter.convertToDto(userDetailsRepository.findByEmail(email));
    }
}
