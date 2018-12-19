package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.Authority;
import com.samsol.cuber.entities.AuthorityName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(AuthorityName name);
}
