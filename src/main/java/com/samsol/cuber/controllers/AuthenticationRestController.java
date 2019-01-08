package com.samsol.cuber.controllers;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.services.crud.ClientCrudService;
import com.samsol.cuber.services.crud.CourierCrudService;
import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.controllers.requests.JwtAuthenticationRequest;
import com.samsol.cuber.controllers.requests.JwtRegistrationRequest;
import com.samsol.cuber.services.security.JwtTokenUtil;
import com.samsol.cuber.services.security.JwtUser;
import com.samsol.cuber.controllers.responses.JwtAuthenticationResponse;
import com.samsol.cuber.controllers.responses.JwtRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsCrudService userDetailsCrudService;

    @Autowired
    private CourierCrudService courierCRUDService;

    @Autowired
    private ClientCrudService clientCRUDService;


    @PostMapping(value = "${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping(value = "${jwt.route.registration.path}")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid JwtRegistrationRequest registrationRequest,  BindingResult result){
        if (result.hasErrors()) return ResponseEntity.badRequest().body(new JwtRegistrationResponse("Not valid data"));
        UserDetailsDto userDetailsDto = userDetailsCrudService.generateNewUserDetails(registrationRequest);
        String username = userDetailsDto.getUsername();
        String email = userDetailsDto.getEmail();
        if (userDetailsCrudService.getUserDetailsByUsername(username) == null && userDetailsCrudService.getUserDetailsByEmail(email) == null) {
            userDetailsCrudService.addUserDetails(userDetailsDto);
            switch (userDetailsDto.getAuthorityName()){
                case ROLE_CLIENT:
                    ClientDto clientDto = new ClientDto();
                    clientDto.setId(0L);
                    clientDto.setUserDetailsId(userDetailsCrudService.getUserDetailsByUsername(username).getId());
                    clientCRUDService.addClient(clientDto);
                    return ResponseEntity.ok().body(new JwtRegistrationResponse("Client was successfully created!"));
                case ROLE_COURIER:
                    CourierDto courierDto = new CourierDto();
                    courierDto.setId(0L);
                    courierDto.setCurrentNodeId(0L);
                    courierDto.setUserDetailsId(userDetailsCrudService.getUserDetailsByUsername(username).getId());
                    courierCRUDService.addCourier(courierDto);
                    return ResponseEntity.ok().body(new JwtRegistrationResponse("Courier was successfully created!"));
            }
        } else {
            return ResponseEntity.badRequest().body(new JwtRegistrationResponse("Such user already exist"));
        }
        return null;
    }



    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Login or password is incorrect", e);
        }
    }
}

