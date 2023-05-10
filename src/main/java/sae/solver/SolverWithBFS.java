package sae.solver;

import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithBFS implements Solver {
    private GraphSoluce soluce;
    private Node start;
    private Node end;
    private int steps;
    public SolverWithBFS(Node start, Node end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public void resolve() {
        Queue<Node> queue = new LinkedList<Node>();
        Set<Node> marques = new HashSet<Node>();
        Map<Node, Node> pred = new HashMap<Node, Node>();

        marques.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node s = queue.poll();
            for (Node v : s.neighbors()) {
                if (!marques.contains(v)) {
                    pred.put(v, s);
                    marques.add(v);
                    queue.add(v);
                    if (v.equals(end)) {
                        soluce = new GraphSoluce();
                        Node node = end;
                        if (pred.containsKey(node)) {
                            while (!pred.equals(start)) {
                                soluce.addNode(node);
                                node = pred.get(node);
                            }
                            soluce.addNode(start);
                        }
                    }
                }
                steps++;
            }
        }
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
