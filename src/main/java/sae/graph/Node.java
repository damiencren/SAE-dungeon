package sae.graph;

import sae.dungeon.Coord;

import java.util.Set;

public class Node {
    private String name;
    private Set<Node> neighbors;
    private Coord coord;

    public Node(String name, Coord coord) {
        this.name = name;
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public boolean equals(Object obj) { //TODO
        return super.equals(obj);
    }

    public Set<Node> neighbors() { //TODO
        Set<Node> var = null;
        return var;
    }

    public void addNeigbour(Node node) {
        //TODO
    }

    @Override
    public String toString() { //TODO
        return super.toString();
    }
}
