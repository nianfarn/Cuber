package com.samsol.cuber.services.crud.impl;

import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.entities.Node;
import com.samsol.cuber.repositories.NodeRepository;
import com.samsol.cuber.services.converters.ConverterService;
import com.samsol.cuber.services.crud.NodeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeCrudServiceImpl implements NodeCrudService {

    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private ConverterService<Node, NodeDto> converter;

    public void addNode(NodeDto nodeDto) {
        nodeRepository.save(converter.convertToEntity(nodeDto));
    }

    public void updateNode(NodeDto nodeDto) {
        nodeRepository.save(converter.convertToEntity(nodeDto));
    }

    public void removeNodeById(long id) {
        nodeRepository.deleteById(id);
    }

    public NodeDto getNodeById(long id) {
        return converter.convertToDto(nodeRepository.findById(id).orElse(null));
    }

    public List<NodeDto> getAllNodes() {
        return converter.convertToDtoList((List<Node>) nodeRepository.findAll());
    }

    @Override
    public NodeDto getNodeDtoByHisLocaleAddress(String address, String locale) {
        switch (locale){
            case "en":
                return converter.convertToDto(nodeRepository.findByAddress(address));
            case "ru":
                return converter.convertToDto(nodeRepository.findByAddressRu(address));
            default:
                return null;
        }
    }
}
