package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.entities.Node;
import com.samsol.cuber.repositories.NodeRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.NodeCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeCRUDServiceImpl implements NodeCRUDService {

    private NodeRepository nodeRepository;
    private ConverterService<Node, NodeDto> converter;

    @Autowired
    public NodeCRUDServiceImpl(NodeRepository nodeRepository, ConverterService<Node, NodeDto> converter) {
        this.nodeRepository = nodeRepository;
        this.converter = converter;
    }

    public void addNodeEntity(NodeDto nodeDto) {
        if (nodeRepository.existsById(nodeDto.getId()))
            System.out.println("This node is already exist!\nUpdated");//todo translate into log
        else
            nodeRepository.save(converter.convertToEntity(nodeDto));
    }

    public void updateNodeEntity(NodeDto nodeDto) {
        if (!nodeRepository.existsById(nodeDto.getId()))
            System.out.println("This delivery order status is not exist! \nCreated"); //todo translate into log
        else
            nodeRepository.save(converter.convertToEntity(nodeDto));
    }

    public void removeNodeEntityById(long id) {
        nodeRepository.deleteById(id);
    }

    public NodeDto getNodeEntityById(long id) {
        return converter.convertToDto(nodeRepository.findById(id).orElse(null));
    }

    public List<NodeDto> getAllNodes() {
        return converter.convertToDtoList((List<Node>) nodeRepository.findAll());
    }
}
