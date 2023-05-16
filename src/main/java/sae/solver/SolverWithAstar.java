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
        this.soluce = new GraphSoluce();
    }

    @Override
    public void resolve() {
        Map<Node, Double> gValues = new HashMap<>();

        Comparator<Node> nodeComparator = (s1, s2) -> {
            double deltaX = s1.getCoord().getX() - end.getCoord().getX();
            double deltaY = s1.getCoord().getY() - end.getCoord().getY();
            double f1 = Math.sqrt(deltaX * deltaX + deltaY * deltaY) + gValues.getOrDefault(s1, Double.POSITIVE_INFINITY);
            deltaX = s2.getCoord().getX() - end.getCoord().getX();
                deltaY = s2.getCoord().getY() - end.getCoord().getY();
            double f2 = Math.sqrt(deltaX * deltaX + deltaY * deltaY) + gValues.getOrDefault(s2, Double.POSITIVE_INFINITY);
            return Double.compare(f1, f2);
        };

        PriorityQueue<Node> openList = new PriorityQueue<>(nodeComparator);
        Set<Node> closeList = new HashSet<>();
        Map<Node, Node> pred = new HashMap<>();

        this.steps = 0;
        gValues.put(start, (double) 0);
        openList.add(end);

        while (!openList.isEmpty()) {
            Node Node = openList.poll();
            if (Node.equals(end)){
                break;
            }
            for (Node neighbor : Node.neighbors()) {
                this.steps++;
                if (!closeList.contains(neighbor)) {
                    double deltaX = Node.getCoord().getX() - neighbor.getCoord().getX();
                    double deltaY = Node.getCoord().getY() - neighbor.getCoord().getY();
                    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    double tentativeG = gValues.get(Node) + distance;

                    if (!openList.contains(neighbor) || tentativeG < gValues.get(neighbor)) {
                        gValues.put(neighbor, tentativeG);
                        pred.put(neighbor, Node);
                        openList.add(neighbor);
                    }
                }
                soluce.add(start);
            }
            closeList.add(Node);
        }
    }



    private double distanceManhattan(Node currentNode, Node end) {
        double x = Math.abs(end.getCoord().getX()) - Math.abs(currentNode.getCoord().getX());
        double y = Math.abs(end.getCoord().getY()) - Math.abs(currentNode.getCoord().getY());
        return Math.abs(x) - Math.abs(y);
    }
    private void reconstructPath(PriorityQueue<Node> openQueue) {
        soluce.add(openQueue.poll());
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
