package sae.solver;

import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithAstar implements Solver {

    private int stepsCount;
    private GraphSoluce soluce;
    private Graph graph;

    SolverWithAstar(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void resolve() {
        stepsCount = 0;

        Queue<Node> closedQueue = new LinkedList<>();
        PriorityQueue<Graph> openQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare (Node node1, Node node2) {
                int dx = Math.abs(node1.getCoord().getX() - node1.getCoord().getY());
                int dy = Math.abs(node1.getCoord().getX()  - coord.getY());
                return dx-dy;
            }
        });

        Map<Node, Node> cameFrom = new HashMap<>();
        openQueue.add(graph.getStartNode());

        while (!openQueue.isEmpty()) {
            Node currentNode = openQueue.poll();
            if (currentNode.equals(graph.getEndNode())) {
                reconstructPath(cameFrom, currentNode);
                break;
            } else {
                closedQueue.add(currentNode);
                for (Node neighbor : currentNode.neighbors()) {
                    if (closedQueue.contains(neighbor))
                        continue;

                    int tentativeGScore = gScore.get(currentNode) + currentNode.getEdgeWeight(neighbor);
                    if (!openQueue.contains(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                        cameFrom.put(neighbor, currentNode);
                        gScore.put(neighbor, tentativeGScore);
                        fScore.put(neighbor, tentativeGScore + neighbor.getHeuristic());

                        if (!openQueue.contains(neighbor))
                            openQueue.add(neighbor);
                    }
                }
            }
        }

        stepsCount = closedQueue.size();
    }

    private void reconstructPath(Map<Node, Node> cameFrom, Node currentNode) {
        LinkedList<Node> path = new LinkedList<>();
        path.addFirst(currentNode);
        while (cameFrom.containsKey(currentNode)) {
            currentNode = cameFrom.get(currentNode);
            path.addFirst(currentNode);
        }
        soluce = new GraphSoluce(path);
    }

    @Override
    public GraphSoluce getSoluce() {
        return soluce;
    }

    @Override
    public int getSteps() {
        return stepsCount;
    }
}
