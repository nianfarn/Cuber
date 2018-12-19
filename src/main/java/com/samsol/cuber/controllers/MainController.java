package com.samsol.cuber.controllers;

import com.samsol.cuber.services.crud.UserDetailsCrudService;
import com.samsol.cuber.services.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsCrudService userDetailsCrudService;

    @GetMapping(value = "/dashboard")
    public ResponseEntity<?> openSpecificDashboard(HttpServletRequest request){
        String authToken = request.getHeader(tokenHeader);
        if(authToken.equals("")) return ResponseEntity.badRequest().body("You need to login first");
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        switch (userDetailsCrudService.getUserDetailsByUsername(username).getAuthorityName()){
            case ROLE_CLIENT:
                return ResponseEntity.ok("client");
            case ROLE_COURIER:
                return ResponseEntity.ok("courier");
            case ROLE_ADMIN:
                return ResponseEntity.ok("admin");
        }
        return null;
    }
}
