package com.dusinski.aoc.solutions.day15;

import java.util.*;

public class Graph {

    private final Set<Node> nodes = new HashSet<>();

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);
        Set<Node> settleNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);
        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacentPair : currentNode.adjacentNodes.entrySet()) {
                Node adjacentNode = adjacentPair.getKey();
                Integer edgeWeight = adjacentPair.getValue();
                if (!settleNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settleNodes.add(currentNode);
        }
        return graph;
    }

    public static Node getLowestDistanceNode(Set<Node> unsettledNode) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNode) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.distance;
        if (sourceDistance + edgeWeigh < evaluationNode.distance) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.shortestPath);
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public Node getNode(Node resultNode) {
        for (Node graphNode : this.nodes) {
            if (graphNode.name.equals(resultNode.name)) {
                return graphNode;
            }
        }
        return null;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }

    public static class Node {
        private final String name;
        private final Map<Node, Integer> adjacentNodes = new HashMap<>();
        private List<Node> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        private int val;

        public Node(String name) {
            this.name = name;
        }

        public Node(String name, int val) {
            this.name = name;
            this.val = val;
        }

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        public String getName() {
            return name;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public int getVal() {
            return val;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", distance=" + distance +
                    ", val=" + val +
                    '}';
        }
    }

}
