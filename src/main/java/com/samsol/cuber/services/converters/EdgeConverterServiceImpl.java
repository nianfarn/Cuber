package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.EdgeDto;
import com.samsol.cuber.entities.Edge;
import com.samsol.cuber.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdgeConverterServiceImpl implements ConverterService<Edge, EdgeDto> {

    private NodeRepository nodeRepository;

    @Autowired
    public EdgeConverterServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public EdgeDto convertToDto(Edge edge) {
        EdgeDto edgeDto = new EdgeDto();
        edgeDto.setId(edge.getId());
        edgeDto.setLength(edge.getLength());
        edgeDto.setFromNodeId(edge.getFromNode().getId());
        edgeDto.setToNodeId(edge.getToNode().getId());
        return edgeDto;
    }

    @Override
    public Edge convertToEntity(EdgeDto edgeDto) {
        final Edge edge = new Edge();
        edge.setId(edgeDto.getId());
        edge.setLength(edgeDto.getLength());
        edge.setFromNode(nodeRepository.findById(edgeDto.getFromNodeId()).orElse(null));
        edge.setToNode(nodeRepository.findById(edgeDto.getToNodeId()).orElse(null));
        return edge;
    }
}
