package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithAstar implements Solver {

    private int steps;
    private GraphSoluce soluce;

    private Node start;
    private Node end;

    public SolverWithAstar(Node start, Node end) {
        super();
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
        Map<Node, Integer> cost = new HashMap<Node, Integer>();
        Map<Node, Integer> heuristic = new HashMap<Node, Integer>();

        openQueue.add(start);

        while (!openQueue.isEmpty()) {
            Node u = openQueue.poll();
            if (u.getCoord().getX() == end.getCoord().getX() && u.getCoord().getY() == end.getCoord().getY()) {
                reconstructPath(openQueue);
                break;
            }
            for (Node v : u.neighbors()) {
                heuristic.put(v, (int) distanceManhattan(v, end));
                cost.put(v, (int) distanceManhattan(v, start));
                if (!closedQueue.contains(v) || !(openQueue.contains(v) && cost.get(v) < cost.get(u))) {
                    cost.put(v, cost.get(u) + 1);
                    heuristic.put(v, cost.get(v) + heuristic.get(v));
                    openQueue.add(v);
                }
            }
            closedQueue.add(u);
        }
        steps++;
    }

    private double distanceManhattan(Node currentNode, Node end) {
        double x = Math.abs(end.getCoord().getX()) - Math.abs(currentNode.getCoord().getX());
        double y = Math.abs(end.getCoord().getY()) - Math.abs(currentNode.getCoord().getY());
        return Math.abs(x) - Math.abs(y);
    }
    private void reconstructPath(PriorityQueue<Node> openQueue) {
        soluce.addNode(openQueue.poll());
    }

    @Override
    public GraphSoluce getSoluce() {
        return this.soluce;
    }

    @Override
    public int getSteps() {
        return steps;
    }
}
