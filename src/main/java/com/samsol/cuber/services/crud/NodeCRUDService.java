package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.NodeDto;

import java.util.List;

public interface NodeCRUDService {
    void addNodeEntity(NodeDto nodeDto);

    void updateNodeEntity(NodeDto nodeDto);

    void removeNodeEntityById(long id);

    NodeDto getNodeEntityById(long id);

    List<NodeDto> getAllNodes();
}
