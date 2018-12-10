package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.DeliveryOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByClientId(Long clientId);

    List<DeliveryOrder> findAllByDeliveryCourierId(Long id);
}
