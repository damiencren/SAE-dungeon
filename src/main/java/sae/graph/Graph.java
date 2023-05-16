package sae.graph;

import sae.dungeon.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Node> nodes = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Nombre de noeuds " + nodes.size();
    }
}
