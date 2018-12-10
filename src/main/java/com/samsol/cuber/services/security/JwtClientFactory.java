package com.samsol.cuber.services.security;

import com.samsol.cuber.dto.ClientDto;
import com.samsol.cuber.entities.AuthorityName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtClientFactory {

    private JwtClientFactory() {
    }

    public static JwtClient create(ClientDto clientDto) {
        List<AuthorityName> authorityNames = new ArrayList<>();
        authorityNames.add(clientDto.getAuthorityName());
        return new JwtClient(
                clientDto.getId(),
                clientDto.getUsername(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getPassword(),
                clientDto.getAge(),
                mapToGrantedAuthorities(authorityNames),
                clientDto.isEnabled(),
                clientDto.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<AuthorityName> authorityNames) {
        return authorityNames.stream()
                .map(authorityName -> new SimpleGrantedAuthority(authorityName.name()))
                .collect(Collectors.toList());
    }
}
