package com.dusinski.aoc.solutions.day15;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.List;

public class Chiton {

//    public int genGraph() {
//        Graph.Node nodeA = new Graph.Node("A");
//        Graph.Node nodeB = new Graph.Node("B");
//        Graph.Node nodeC = new Graph.Node("C");
//        Graph.Node nodeD = new Graph.Node("D");
//        Graph.Node nodeE = new Graph.Node("E");
//        Graph.Node nodeF = new Graph.Node("F");
//        nodeA.addDestination(nodeB, 10);
//        nodeA.addDestination(nodeC, 15);
//        nodeB.addDestination(nodeD, 12);
//        nodeB.addDestination(nodeF, 15);
//        nodeC.addDestination(nodeE, 10);
//        nodeD.addDestination(nodeE, 2);
//        nodeD.addDestination(nodeF, 1);
//        nodeF.addDestination(nodeE, 5);
//        Graph graph = new Graph();
//        graph.addNode(nodeA);
//        graph.addNode(nodeB);
//        graph.addNode(nodeC);
//        graph.addNode(nodeD);
//        graph.addNode(nodeE);
//        graph.addNode(nodeF);
//        graph = Graph.calculateShortestPathFromSource(graph, nodeA);
//        return graph.getNode(nodeD).getDistance();
//    }

    public byte[][] loadData() {
        List<String> inputList = DataLoadUtil.loadStringList("day15.txt");
//        List<String> inputList = DataLoadUtil.loadStringList("test2.txt");
        int rowNum = inputList.size();
        int colNum = inputList.get(0).length();
        byte[][] riskMap = new byte[rowNum][colNum];
        for (int i = 0; i < inputList.size(); i++) {
            String s = inputList.get(i);
            for (int j = 0; j < s.length(); j++) {
                riskMap[i][j] = Byte.parseByte(s.charAt(j) + "");
            }
        }
        return riskMap;
    }

    public int genGraphPart1() {
        byte[][] riskMap = loadData();
        Graph.Node[][] nodeArray = createNodeArray(riskMap);
        Graph graph1 = fillGraph(riskMap, nodeArray);

        graph1 = Graph.calculateShortestPathFromSource(graph1, nodeArray[0][0]);
        Graph.Node firstNode = nodeArray[0][0];
        Graph.Node lastNode = nodeArray[nodeArray.length - 1][nodeArray[0].length - 1];
//        Graph.Node anotherNode = nodeArray[1][1];
        return lastNode.getDistance() - firstNode.getVal() + lastNode.getVal();
    }

    private Graph.Node[][] createNodeArray(byte[][] rM) {
        Graph.Node[][] nodeArray = new Graph.Node[rM.length][rM.length];
        for (int i = 0; i < rM.length; i++) {
            for (int j = 0; j < rM[i].length; j++) {
                Graph.Node newNode = new Graph.Node(i + "-" + j, rM[i][j]);
                nodeArray[i][j] = newNode;
            }
        }
        return nodeArray;
    }

    private Graph fillGraph(byte[][] rM, Graph.Node[][] nA) {
        Graph g = new Graph();
        for (int i = 0; i < rM.length; i++) {
            for (int j = 0; j < rM[i].length; j++) {
                Graph.Node currentNode = nA[i][j];
                List<Graph.Node> adjacentNodes = getAdjacentNodes(i, j,nA);
                for (Graph.Node n : adjacentNodes) {
                    currentNode.addDestination(n, currentNode.getVal());
                }
                g.addNode(currentNode);
            }
        }
        return g;
    }

    private List<Graph.Node> getAdjacentNodes(int row, int column, Graph.Node[][] nA) {
        List<Graph.Node> adjacentNodesList = new ArrayList<>();
        if (row + 1 < nA.length) {
            adjacentNodesList.add(nA[row + 1][column]);
        }
        if (column + 1 < nA[row].length) {
            adjacentNodesList.add(nA[row][column + 1]);
        }

        if (row - 1 > 0) {
            adjacentNodesList.add(nA[row - 1][column]);
        }
        if (column - 1 > 0) {
            adjacentNodesList.add(nA[row][column - 1]);
        }

        return adjacentNodesList;
    }

    private String printArray(byte[][] rM) {
        StringBuilder sb = new StringBuilder();
        for (byte[] bytes : rM) {
            for (int j = 0; j < bytes.length; j++) {
                sb.append(bytes[j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
