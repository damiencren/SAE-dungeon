package sae.graph;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph() {
        super();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2) {
        node1.addNeighbour(node2);
        node2.addNeighbour(node1);
    }
}
