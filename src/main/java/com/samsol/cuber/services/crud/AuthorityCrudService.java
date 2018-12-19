package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.AuthorityDto;
import com.samsol.cuber.entities.AuthorityName;

import java.util.List;

public interface AuthorityCrudService {
    void addAuthority(AuthorityDto authorityDto);

    void updateAuthority(AuthorityDto authorityDto);

    void removeAuthorityById(long id);

    AuthorityDto getAuthorityById(int id);

    List<AuthorityDto> getAllAuthorities();

    AuthorityName getAuthorityNameById(int authorityNameId);
}
