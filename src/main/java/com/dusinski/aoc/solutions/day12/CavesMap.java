package com.dusinski.aoc.solutions.day12;

import java.util.*;

public class CavesMap {
    public CaveNode startNode;
    public CaveNode endNode;
    public Map<CaveNode, List<CaveNode>> caveNodeListMap;

    public CavesMap(List<String> input) {
        this.caveNodeListMap = new LinkedHashMap<>();
        for (String i : input) {
            String a = i.split("-")[0];
            String b = i.split("-")[1];
            CaveNode aNode = new CaveNode(a);
            CaveNode bNode = new CaveNode(b);

            if (!this.caveNodeListMap.containsKey(aNode)) {
                ArrayList<CaveNode> nodeList = new ArrayList<>();
                nodeList.add(bNode);
                this.caveNodeListMap.put(aNode, nodeList);
            } else {
                List<CaveNode> nodeList = this.caveNodeListMap.get(aNode);
                nodeList.add(bNode);
            }

            if (!this.caveNodeListMap.containsKey(bNode)) {
                ArrayList<CaveNode> nodeList = new ArrayList<>();
                nodeList.add(aNode);
                this.caveNodeListMap.put(bNode, nodeList);
            } else {
                List<CaveNode> nodeList = this.caveNodeListMap.get(bNode);
                nodeList.add(aNode);
            }
        }
        getStartAndEnd();
    }

    private void getStartAndEnd() {
        for (Map.Entry<CaveNode, List<CaveNode>> me : this.caveNodeListMap.entrySet()) {
            if (me.getKey().name.equals("start")) {
                this.startNode = me.getKey();
            }
            if (me.getKey().name.equals("end")) {
                this.endNode = me.getKey();
            }
        }
    }

    public int getPathsCount() {

        CheckSmallCaveFunction checkSmallCaveFunction = (node, map) -> (map.containsKey(node) && map.get(node) == 2);

        Map<CaveNode, Set<CaveNode>> visitedEdgeMap = new LinkedHashMap<>();
        String path = "start";
        Map<CaveNode, Integer> smallNodesMap = new LinkedHashMap<>();
        return printAllP(checkSmallCaveFunction, this.startNode, visitedEdgeMap, path, smallNodesMap);
    }

    public int getPathsCountPart2() {

        CheckSmallCaveFunction checkSmallCaveFunction = (node, map) -> {

            int occurenceCount = 0;
            for (Map.Entry<CaveNode, Integer> mapEntry : map.entrySet()) {
                if (mapEntry.getValue() == 2) {
                    occurenceCount++;
                }
                if (mapEntry.getValue() > 2) {
                return true;
                }
            }
//            System.out.println("occurenceCount: "+occurenceCount);
            System.out.println("map: "+map+" node: "+node+"occurenceCount: "+occurenceCount);
            if (occurenceCount > 1){
                return true;
            }

            return  !(map.get(node) <= 3);
//            return  false;
        };


//        CheckSmallCaveFunction checkSmallCaveFunction = (node, map) -> (map.containsKey(node) && map.get(node) == 2);

//        Map<CaveNode, Set<CaveNode>> visitedEdgeMap = new LinkedHashMap<>();
        Map<Edge, Integer> visitedEdgeMap = new LinkedHashMap<>();

        String path = "start";
        Map<CaveNode, Integer> smallNodesMap = new LinkedHashMap<>();
        return printAllPVer2(checkSmallCaveFunction, this.startNode, visitedEdgeMap, path, smallNodesMap);
    }


    private int printAllP(CheckSmallCaveFunction function, CaveNode inputCaveNode, Map<CaveNode, Set<CaveNode>> visitedEdgeMap, String p, Map<CaveNode, Integer> smallMap) {
        int counter = 0;
        if (inputCaveNode.equals(this.endNode)) {
            System.out.println(p);
            return 1;
        }

        if (inputCaveNode.isSmall) {
            if (function.check(inputCaveNode, smallMap)) {
                return 0;
            }
        }

        for (CaveNode c : this.caveNodeListMap.get(inputCaveNode)) {
            if (!c.equals(this.startNode)) {
                if (!visitedEdgeMap.containsKey(inputCaveNode) || !visitedEdgeMap.get(inputCaveNode).contains(c)) {
                    if (c.isSmall) {
                        if (!smallMap.containsKey(c)) {
                            smallMap.put(c, 1);
                        } else {
                            int currentVal = smallMap.get(c) + 1;
                            smallMap.put(c, currentVal);
                        }
                    }

                    Set<CaveNode> caveNodeSet;
                    if (!visitedEdgeMap.containsKey(inputCaveNode)) {
                        caveNodeSet = new HashSet<>();
                    } else {
                        caveNodeSet = visitedEdgeMap.get(inputCaveNode);
                    }
                    caveNodeSet.add(c);
                    visitedEdgeMap.put(inputCaveNode, caveNodeSet);
                    counter = counter + printAllP(function, c, visitedEdgeMap, p + " " + c.name, smallMap);
                    caveNodeSet = visitedEdgeMap.get(inputCaveNode);
                    caveNodeSet.remove(c);
                    visitedEdgeMap.put(inputCaveNode, caveNodeSet);

                    if (c.isSmall) {
                        int currentVal = smallMap.get(c) - 1;
                        smallMap.put(c, currentVal);
                    }
                }
            }
        }
        return counter;
    }

    private boolean onlyOneEdgeLessThenTwo(Map<Edge, Integer> visitedEdgeMap, Edge tempEdge) {
        int biggerThenTwoCounter = 0;
//        System.out.println(visitedEdgeMap);
        for (Map.Entry<Edge, Integer> mapEntry : visitedEdgeMap.entrySet()) {

            if (mapEntry.getValue() > 1) {
                biggerThenTwoCounter++;
            }
        }
//        System.out.println("biggerThenTwoCounter: " + biggerThenTwoCounter);
//        return visitedEdgeMap.get(tempEdge) < 2;
        return biggerThenTwoCounter < 2 && visitedEdgeMap.get(tempEdge) <2;
    }

    private int printAllPVer2(CheckSmallCaveFunction function, CaveNode inputCaveNode, Map<Edge, Integer> visitedEdgeMap, String p, Map<CaveNode, Integer> smallMap) {
//        Map<Edge,Integer> visitedEdgeMap = new LinkedHashMap<>();

        int counter = 0;
        if (inputCaveNode.equals(this.endNode)) {
            System.out.println(p);
            return 1;
        }

        if (inputCaveNode.isSmall) {
            if (function.check(inputCaveNode, smallMap)) {
                return 0;
            }
        }

        for (CaveNode c : this.caveNodeListMap.get(inputCaveNode)) {
            if (!c.equals(this.startNode)) {
//                if (!visitedEdgeMap.containsKey(inputCaveNode) || !visitedEdgeMap.get(inputCaveNode).contains(c)) {
                Edge tempEdge = new Edge(inputCaveNode, c);

//                if (!visitedEdgeMap.containsKey(tempEdge)||visitedEdgeMap.get(tempEdge)<2) {
                if (!visitedEdgeMap.containsKey(tempEdge) || onlyOneEdgeLessThenTwo(visitedEdgeMap, tempEdge)) {
                    if (c.isSmall) {
                        if (!smallMap.containsKey(c)) {
                            smallMap.put(c, 1);
                        } else {
                            int currentVal = smallMap.get(c) + 1;
                            smallMap.put(c, currentVal);
                        }
                    }

//                    Set<CaveNode> caveNodeSet;
                    int cntr = 0;
                    if (!visitedEdgeMap.containsKey(tempEdge)) {
                        cntr = 0;
                    } else {
                        cntr = visitedEdgeMap.get(tempEdge);
                    }
                    cntr++;
//                    caveNodeSet.add(c);
                    visitedEdgeMap.put(tempEdge, cntr);
                    counter = counter + printAllPVer2(function, c, visitedEdgeMap, p + " " + c.name, smallMap);
//                    caveNodeSet = visitedEdgeMap.get(inputCaveNode);
                    cntr = visitedEdgeMap.get(tempEdge);
//                    caveNodeSet.remove(c);
                    cntr--;
                    visitedEdgeMap.put(tempEdge, cntr);

                    if (c.isSmall) {
                        int currentVal = smallMap.get(c) - 1;
                        smallMap.put(c, currentVal);
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return "CavesMap{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", caveNodeListMap=" + caveNodeListMap +
                '}';
    }

    private interface CheckSmallCaveFunction {
        boolean check(CaveNode smallNode, Map<CaveNode, Integer> smallMap);
    }
//if (smallMap.containsKey(inputCaveNode) && smallMap.get(inputCaveNode) == 2) {

    private class Edge {
        public CaveNode start;
        public CaveNode end;

        public Edge(CaveNode start, CaveNode end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return start.equals(edge.start) && end.equals(edge.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    private class CaveNode {
        public String name;
        public boolean isSmall;

        public CaveNode(String n) {
            this.name = n;
            this.isSmall = isSmall(n);
        }

        private boolean isSmall(String n) {
            if (!n.equals("start") && !n.equals("end")) {
                return n.toLowerCase().equals(n);
            } else return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CaveNode)) return false;
            CaveNode caveNode = (CaveNode) o;
            return name.equals(caveNode.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "CaveNode{" +
                    "name='" + name + '\'' +
//                    ", isSmall=" + isSmall +
                    '}';
        }
    }
}


