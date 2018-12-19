package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
