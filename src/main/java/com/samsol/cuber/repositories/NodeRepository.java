package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {
}
