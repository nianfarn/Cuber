package com.samsol.cuber.repositories;

import com.samsol.cuber.dto.UserDetailsDto;
import com.samsol.cuber.entities.Client;
import com.samsol.cuber.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByClientDetailsId(Long id);
}
