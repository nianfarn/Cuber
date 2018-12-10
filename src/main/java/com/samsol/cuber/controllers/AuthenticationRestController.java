package com.samsol.cuber.controllers;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.entities.Client;
import com.samsol.cuber.services.crud.ClientCRUDService;
import com.samsol.cuber.services.crud.CourierCRUDService;
import com.samsol.cuber.services.security.JwtAuthenticationRequest;
import com.samsol.cuber.services.security.JwtRegistrationRequest;
import com.samsol.cuber.services.security.JwtTokenUtil;
import com.samsol.cuber.services.security.JwtClient;
import com.samsol.cuber.services.security.responces.JwtAuthenticationResponse;
import com.samsol.cuber.services.security.responces.JwtRegistrationResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Qualifier("jwtClientDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CourierCRUDService courierCRUDService;

    @Autowired
    private ClientCRUDService clientCRUDService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.registration.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody @Valid JwtRegistrationRequest registrationRequest,  BindingResult result){
        if (result.hasErrors()) return ResponseEntity.badRequest().body(new JwtRegistrationResponse("Not valid data"));
        String username = registrationRequest.getUsername();
        if(clientCRUDService.getClientByUsername(username)==null){
            ClientDto clientDto = clientCRUDService.generateNewClient(registrationRequest);
            clientCRUDService.addClient(clientDto);
            return ResponseEntity.ok().body(new JwtRegistrationResponse("Client was successfully created!"));
        }else{
            return ResponseEntity.badRequest().body(new JwtRegistrationResponse("Already exist"));
        }

    }


    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtClient user = (JwtClient) userDetailsService.loadUserByUsername(username);

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
