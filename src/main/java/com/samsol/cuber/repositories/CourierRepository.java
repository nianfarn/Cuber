package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.Courier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {
    Courier findByCourierDetailsId(Long id);
    List<Courier> findAllByReadyToGo(boolean bool);
}
