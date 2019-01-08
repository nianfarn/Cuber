package com.samsol.cuber.services.logic.helpers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NodeForMapService {

    private Long id;
    private List<NodeForMapService> shortestPath = new LinkedList<>();
    private Double distance = Double.MAX_VALUE;

    Map<NodeForMapService, Double> adjacentNodes = new HashMap<>();

    public void addDestination(NodeForMapService destination, Double distance) {
        adjacentNodes.put(destination, distance);
    }

    public NodeForMapService(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NodeForMapService> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<NodeForMapService> shortestPath) {
        this.shortestPath = shortestPath;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<NodeForMapService, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<NodeForMapService, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}