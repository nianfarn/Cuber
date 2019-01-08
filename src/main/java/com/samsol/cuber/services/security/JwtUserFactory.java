package com.samsol.cuber.services.security;

import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.AuthorityName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public JwtUser create(UserDetailsDto userDetailsDto) {
        List<AuthorityName> authorityNames = new ArrayList<>();
        authorityNames.add(userDetailsDto.getAuthorityName());
        return new JwtUser(
                userDetailsDto.getId(),
                userDetailsDto.getUsername(),
                userDetailsDto.getFirstName(),
                userDetailsDto.getLastName(),
                userDetailsDto.getEmail(),
                userDetailsDto.getPassword(),
                userDetailsDto.getAge(),
                mapToGrantedAuthorities(authorityNames),
                userDetailsDto.getEnabled(),
                userDetailsDto.getLastPasswordResetDate());
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<AuthorityName> authorityNames) {
        return authorityNames.stream()
                .map(authorityName -> new SimpleGrantedAuthority(authorityName.name()))
                .collect(Collectors.toList());
    }
}
