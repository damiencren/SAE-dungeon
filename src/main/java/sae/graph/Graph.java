package sae.graph;

import java.util.ArrayList;

public class Graph {

    ArrayList<Node> nodes;

    public Graph() {
        super();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    addEdge(Node node1, Node node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }
}
