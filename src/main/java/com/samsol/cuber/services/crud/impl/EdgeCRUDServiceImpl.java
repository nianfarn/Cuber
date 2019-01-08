package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.EdgeDto;
import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.entities.Edge;
import com.samsol.cuber.entities.Node;
import com.samsol.cuber.repositories.EdgeRepository;
import com.samsol.cuber.repositories.NodeRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.EdgeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeCrudServiceImpl implements EdgeCrudService {

    @Autowired
    private EdgeRepository edgeRepository;
    @Autowired
    private ConverterService<Edge, EdgeDto> converter;
    @Autowired
    private ConverterService<Node, NodeDto> nodeConverter;

    public void addEdgeEntity(EdgeDto edgeDto) {
        edgeRepository.save(converter.convertToEntity(edgeDto));
    }

    public void updateEdgeEntity(EdgeDto edgeDto) {
        edgeRepository.save(converter.convertToEntity(edgeDto));
    }

    public void removeEdgeEntityById(long id) {
        edgeRepository.deleteById(id);
    }

    public EdgeDto getEdgeEntityById(long id) {
        return converter.convertToDto(edgeRepository.findById(id).orElse(null));
    }

    public List<EdgeDto> getAllEdges() {
        return converter.convertToDtoList((List<Edge>) edgeRepository.findAll());
    }

    public List<EdgeDto> getAllEdgesFromNode(NodeDto fromNodeDto) {
        return converter.convertToDtoList(edgeRepository.findAllByFromNode(nodeConverter.convertToEntity(fromNodeDto)));
    }

    public EdgeDto getEdgeFromTo(NodeDto prevNodeDto, NodeDto toNodeDto) {
        Node prevNode = nodeConverter.convertToEntity(prevNodeDto);
        Node toNode = nodeConverter.convertToEntity(toNodeDto);
        return converter.convertToDto(edgeRepository.findByFromNodeAndToNodeEquals(prevNode, toNode));
    }
}
