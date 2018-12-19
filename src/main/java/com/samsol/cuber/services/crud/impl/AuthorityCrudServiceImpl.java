package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.AuthorityDto;
import com.samsol.cuber.entities.Authority;
import com.samsol.cuber.entities.AuthorityName;
import com.samsol.cuber.repositories.AuthorityRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.AuthorityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityCrudServiceImpl implements AuthorityCrudService {

    @Autowired
    private AuthorityRepository repository;
    @Autowired
    private ConverterService<Authority, AuthorityDto> converter;

    @Override
    public void addAuthority(AuthorityDto authorityDto) {

    }

    @Override
    public void updateAuthority(AuthorityDto authorityDto) {

    }

    @Override
    public void removeAuthorityById(long id) {

    }

    @Override
    public AuthorityDto getAuthorityById(int id) {
        return null;
    }

    @Override
    public List<AuthorityDto> getAllAuthorities() {
        return null;
    }

    @Override
    public AuthorityName getAuthorityNameById(int authorityNameId) {
        return null;
    }
}
