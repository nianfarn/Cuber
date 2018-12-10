package com.samsol.cuber.services.logic.tools;

import java.util.HashSet;
import java.util.Set;

public class GraphForMapService {

    private Set<NodeForMapService> nodes = new HashSet<>();

    public void addNode(NodeForMapService nodeA) {
        nodes.add(nodeA);
    }

    public Set<NodeForMapService> getNodes() {
        return nodes;
    }

    public boolean nodeIsExistById(Long id) {
        for (NodeForMapService node : nodes) {
            if (node.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public NodeForMapService getNodeById(Long id) {
        for (NodeForMapService node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    public void setNodes(Set<NodeForMapService> nodes) {
        this.nodes = nodes;
    }
}
