package org.example.firstdemo.controller.problemsolving;

import java.util.List;

public class Graph {
    private List<Node> nodeList;
    private List<Edge> edgeList;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodeList = nodes;
        this.edgeList = edges;
    }

    public void setNodes(List<Node> nodes) {
        this.nodeList = nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edgeList = edges;
    }

    public List<Node> getNodes() {
        return nodeList;
    }

    public List<Edge> getEdges() {
        return edgeList;
    }

    public static class Node {
        private String id;
        private String label;
        private int x;
        private int y;
        private int size;
        private String color;

        public Node(String nID, String name, int nodeX, int nodeY, int sz, String col) {
            this.id = nID;
            this.label = name;
            this.size = sz;
            this.x = nodeX;
            this.y = nodeY;
            this.color = col;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id='" + id + '\'' +
                    ", label='" + label + '\'' +
                    ", x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    public static class Edge {
        private String id;
        private String source;
        private String target;
        private String color;

        public Edge(String edgeName, String firstNode, String secondNode, String col) {
            this.id = edgeName;
            this.source = firstNode;
            this.target = secondNode;
            this.color = col;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getSource() {
            return source;
        }

        public String getTarget() {
            return target;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "id='" + id + '\'' +
                    ", source='" + source + '\'' +
                    ", target='" + target + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
    @Override
    public String toString(){
        return "Graph has "+nodeList.size()+" nodes and "+edgeList.size()+" edges\n"+nodeList.toString()+"\n&&&\n"+edgeList.toString();
    }
}
