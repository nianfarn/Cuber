package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.EdgeDto;
import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.entities.Edge;
import com.samsol.cuber.entities.Node;
import com.samsol.cuber.repositories.EdgeRepository;
import com.samsol.cuber.repositories.NodeRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.EdgeCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeCRUDServiceImpl implements EdgeCRUDService {

    private EdgeRepository edgeRepository;
    private NodeRepository nodeRepository;
    private ConverterService<Edge, EdgeDto> converter;
    private ConverterService<Node, NodeDto> nodeConverter;

    @Autowired
    public EdgeCRUDServiceImpl(EdgeRepository edgeRepository, NodeRepository nodeRepository, ConverterService<Edge, EdgeDto> converter, ConverterService<Node, NodeDto> nodeConverter) {
        this.edgeRepository = edgeRepository;
        this.nodeRepository = nodeRepository;
        this.converter = converter;
        this.nodeConverter = nodeConverter;
    }

    public void addEdgeEntity(EdgeDto edgeDto) {
        if (edgeRepository.existsById(edgeDto.getId()))
            System.out.println("This transition is already exist!\nUpdated");//todo translate into log
        else
            edgeRepository.save(converter.convertToEntity(edgeDto));
    }

    public void updateEdgeEntity(EdgeDto edgeDto) {
        if (!edgeRepository.existsById(edgeDto.getId()))
            System.out.println("This transition is not exist! \nCreated"); //todo translate into log
        else
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
