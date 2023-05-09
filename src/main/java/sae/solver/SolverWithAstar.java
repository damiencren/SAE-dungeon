package sae.solver;

import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithAstar implements Solver {

    private int steps;
    private GraphSoluce soluce;

    private Node start;
    private Node end;

    public SolverWithAstar(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void resolve() {
        steps = 0;

        Queue<Node> closedQueue = new LinkedList<>();
        PriorityQueue<Node> openQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                int dx = Math.abs(node1.getCoord().getX() - node1.getCoord().getY());
                int dy = Math.abs(node2.getCoord().getX() - node2.getCoord().getY());
                return dx - dy;
            }
        });

        openQueue.add(graph.getStartNode());

        while (!openQueue.isEmpty()) {
            Node currentNode = openQueue.poll();
            if (currentNode.equals(graph.getEndNode())) {
                soluce = new GraphSoluce();
                soluce.addNode(currentNode);
                while (currentNode.neighbors() != null) {
                    soluce.addNode(currentNode.getParent());
                    currentNode = currentNode.getParent();
                }
                break;
            }
            for (Node node : currentNode.neighbors()) {
                if (!closedQueue.contains(node)) {
                    node.setParent(currentNode);
                    openQueue.add(node);
                }
            }
            closedQueue.add(currentNode);
        }

        //stepsCount++;
    }

    @Override
    public GraphSoluce getSoluce() {
        return soluce;
    }

    @Override
    public int getSteps() {
        return steps;
    }
}
