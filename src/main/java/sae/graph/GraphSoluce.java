package sae.graph;

import java.util.List;

public class GraphSoluce {
    private List<Node> soluce;

    public GraphSoluce(List<Node> soluce) {
        this.soluce = soluce;
    }

    public void addNode(Node node) {
        soluce.add(node);
    }

    public List<Node> getSoluce() {
        return soluce;
    }
}