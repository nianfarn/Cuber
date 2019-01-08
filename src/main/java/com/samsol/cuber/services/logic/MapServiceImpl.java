package com.samsol.cuber.services.logic;

import com.samsol.cuber.dto.CourierDto;
import com.samsol.cuber.dto.DeliveryOrderDto;
import com.samsol.cuber.dto.EdgeDto;
import com.samsol.cuber.services.crud.CourierCrudService;
import com.samsol.cuber.services.crud.EdgeCrudService;
import com.samsol.cuber.services.crud.NodeCrudService;
import com.samsol.cuber.services.logic.helpers.GraphForMapService;
import com.samsol.cuber.services.logic.helpers.NodeForMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;


@Service
public class MapServiceImpl implements MapService {

    private static final Double PRICE_MULTIPLIER = 3.4;

    private final NodeCrudService nodeCRUDService;
    private final EdgeCrudService edgeCRUDService;
    private final CourierCrudService courierCRUDService;

    @Autowired
    public MapServiceImpl(NodeCrudService nodeCRUDService, EdgeCrudService edgeCRUDService, CourierCrudService courierCRUDService) {
        this.nodeCRUDService = nodeCRUDService;
        this.edgeCRUDService = edgeCRUDService;
        this.courierCRUDService = courierCRUDService;
    }

    public CourierDto findClosestCourier(DeliveryOrderDto deliveryOrderDto) {
        List<CourierDto> listCouriers = courierCRUDService.getAllCouriers();
        CourierDto finest = null;
        Double minLength = Double.MAX_VALUE;

        for (CourierDto c : listCouriers) {
            long courierNodeId = c.getCurrentNodeId();
            long orderFromId = deliveryOrderDto.getFromNodeId();

            Double length = calculateLength(courierNodeId, orderFromId);

            if (length < minLength) {
                minLength = length;
                finest = c;
            }
        }
        return finest;
    }

    @Override
    public double calculatePrice(Double length) {
        return length * PRICE_MULTIPLIER;
    }

    public Double calculateLength(Long fromId, Long toId) {
        GraphForMapService graph = generateGraphFromNodeWithId(fromId);
        graph.getNodeById(fromId).setDistance(0.);

        Set<NodeForMapService> settledNodes = new HashSet<>();
        Set<NodeForMapService> unsettledNodes = new HashSet<>();

        unsettledNodes.add(graph.getNodeById(fromId));

        while (unsettledNodes.size() != 0) {
            NodeForMapService currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<NodeForMapService, Double> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                NodeForMapService adjacentNode = adjacencyPair.getKey();
                Double edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph.getNodeById(toId).getDistance();
    }

    private NodeForMapService getLowestDistanceNode(Set<NodeForMapService> unsettledNodes) {
        NodeForMapService lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;
        for (NodeForMapService node : unsettledNodes) {
            Double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(NodeForMapService evaluationNode,
                                          Double edgeWeigh, NodeForMapService sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<NodeForMapService> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private GraphForMapService generateGraphFromNodeWithId(Long id) {
        GraphForMapService graph = new GraphForMapService();
        Queue<Long> queue = new ArrayDeque<>();

        NodeForMapService firstNode = new NodeForMapService(id);
        firstNode.setDistance(0.);
        graph.addNode(new NodeForMapService(id));
        queue.add(id);
        while (!queue.isEmpty()) {
            Long nodeId = queue.poll();
            NodeForMapService node = graph.getNodeById(nodeId);
            List<EdgeDto> nodeAdj = edgeCRUDService.getAllEdgesFromNode(nodeCRUDService.getNodeById(nodeId));
            for (EdgeDto edge : nodeAdj) {
                Long toNodeId = edge.getToNodeId();

                if (graph.nodeIsExistById(toNodeId)) {
                    node.addDestination(graph.getNodeById(toNodeId), edge.getLength());
                } else {
                    NodeForMapService toNode = new NodeForMapService(toNodeId);
                    node.addDestination(toNode, edge.getLength());
                    queue.add(toNodeId);

                    graph.addNode(toNode);
                }
            }
        }

        return graph;
    }
}