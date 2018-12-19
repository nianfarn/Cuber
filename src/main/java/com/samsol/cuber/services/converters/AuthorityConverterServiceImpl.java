package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.AuthorityDto;
import com.samsol.cuber.entities.Authority;
import com.samsol.cuber.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityConverterServiceImpl implements ConverterService<Authority,AuthorityDto> {

    @Autowired
    private AuthorityRepository repository;

    @Override
    public AuthorityDto convertToDto(Authority authority) {
        if(authority==null) return null;
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setAuthorityName(authority.getName());
        return authorityDto;
    }

    @Override
    public Authority convertToEntity(AuthorityDto authorityDto) {
        Authority authority = new Authority();
        authority.setId(authorityDto.getId());
        authority.setName(repository.findByName(authorityDto.getAuthorityName()).getName());
        return authority;
    }
}
