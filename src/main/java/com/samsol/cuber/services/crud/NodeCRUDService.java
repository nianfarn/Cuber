package com.samsol.cuber.services.crud;

import com.samsol.cuber.dto.NodeDto;

import java.util.List;

public interface NodeCrudService {
    void addNode(NodeDto nodeDto);

    void updateNode(NodeDto nodeDto);

    void removeNodeById(long id);

    NodeDto getNodeById(long id);

    List<NodeDto> getAllNodes();
    
    NodeDto getNodeDtoByHisLocaleAddress(String address, String locale);
}
