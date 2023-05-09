package sae.graph;

import sae.dungeon.Room;

import java.util.ArrayList;
import java.util.Map;

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


    public Node getNodeByName(String name) {
            for (Node node : nodes) {
                if (node.getName().equals(name)) {
                    return node;
                }
            }
            return null;
        }


}
