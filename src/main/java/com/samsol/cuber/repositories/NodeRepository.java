package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {
    Node findByAddress(String address);
    Node findByAddressRu(String address);
}
