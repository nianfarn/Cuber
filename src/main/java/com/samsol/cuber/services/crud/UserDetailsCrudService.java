package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.controllers.requests.JwtRegistrationRequest;

import java.util.List;

public interface UserDetailsCrudService{
    void addUserDetails(UserDetailsDto userDetailsDto);

    void updateUserDetails(UserDetailsDto userDetailsDto);

    void removeUserDetailsById(long id);

    UserDetailsDto getUserDetailsById(long id);

    List<UserDetailsDto> getAllUserDetails();

    UserDetailsDto generateNewUserDetails(JwtRegistrationRequest registrationRequest);

    UserDetailsDto getUserDetailsByUsername(String username);

    UserDetailsDto getUserDetailsByEmail(String email);
}
