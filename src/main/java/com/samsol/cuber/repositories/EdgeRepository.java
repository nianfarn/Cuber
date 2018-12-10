package com.samsol.cuber.repositories;

import com.samsol.cuber.entities.Edge;
import com.samsol.cuber.entities.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EdgeRepository extends CrudRepository<Edge, Long> {
    ArrayList<Edge> findAllByToNode(Node node);

    ArrayList<Edge> findAllByFromNode(Node fromNode);

    Edge findByFromNodeAndToNodeEquals(Node prevNode, Node node);
}
