package com.samsol.cuber.services.converters;

import com.samsol.cuber.dto.NodeDto;
import com.samsol.cuber.entities.Node;
import org.springframework.stereotype.Service;

@Service
public class NodeConverterServiceImpl implements ConverterService<Node, NodeDto> {

    @Override
    public NodeDto convertToDto(Node node) {
        NodeDto nodeDto = new NodeDto();
        nodeDto.setId(node.getId());
        nodeDto.setAddress(node.getAddress());
        nodeDto.setAddressRu(node.getAddressRu());
        return nodeDto;
    }

    @Override
    public Node convertToEntity(NodeDto nodeDto) {
        final Node node = new Node();
        node.setId(nodeDto.getId());
        node.setAddress(nodeDto.getAddress());
        node.setAddressRu(nodeDto.getAddressRu());
        return node;
    }
}
