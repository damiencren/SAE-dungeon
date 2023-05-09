package sae.transform;

import sae.dungeon.Dungeon;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.Set;

public class Dungeon2Graph {

    private Dungeon dungeon;

    public Dungeon2Graph(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Graph transform(GraphSoluce graphSoluce) {
        Graph graph = new Graph();

        // Convertir les salles en nœuds du graphe
        for (Room room : dungeon.getRooms()) {
            Node node = new Node(room.getName(), room.getCoords());
            graph.addNode(node);
            // Si la salle correspond à la salle A ou B du donjon, la définir comme nœud de départ ou d'arrivée
            if (room.equals(dungeon.getRoomA())) {
                graph.setStartNode(node);
            }
            if (room.equals(dungeon.getRoomB())) {
                graph.setEndNode(node);
            }
        }

        // Ajouter les arêtes du graphe en utilisant les connexions entre les salles du donjon
        for (Room room : dungeon.getRooms()) {
            Node node1 = graph.getNodeByName(room.getName());

            for (Room connectedRoom : room.getNextRooms().values()) {
                Node node2 = graph.getNodeByName(connectedRoom.getName());
                graph.addEdge(node1, node2);
            }
        }

        return graph;
    }
}
