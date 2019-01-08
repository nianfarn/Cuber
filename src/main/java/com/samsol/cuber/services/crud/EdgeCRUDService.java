package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.EdgeDto;
import com.samsol.cuber.dto.NodeDto;

import java.util.List;

public interface EdgeCrudService {
    void addEdgeEntity(EdgeDto edgeDto);

    void updateEdgeEntity(EdgeDto edgeDto);

    void removeEdgeEntityById(long id);

    EdgeDto getEdgeEntityById(long id);

    List<EdgeDto> getAllEdges();

    List<EdgeDto> getAllEdgesFromNode(NodeDto toNodeDto);

    EdgeDto getEdgeFromTo(NodeDto prevNodeDto, NodeDto nodeDto);
}
