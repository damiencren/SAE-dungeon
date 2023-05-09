package sae.transform;

import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dungeon2Graph {

    private Graph graph;

    private Map<Room, Node> roomNode = new HashMap<>();

    public Dungeon2Graph(Dungeon dungeon){
        for (Room room : dungeon.getRooms()) {
            Node node = new Node(room.getName(), room.getCoords());
            roomNode.put(room, node);
            graph.addNode(node);
            // Si la salle correspond à la salle A ou B du donjon, la définir comme nœud de départ ou d'arrivée
            if (room.equals(dungeon.getRoomA())) {
                graph.setStartNode(node);
            }
            if (room.equals(dungeon.getRoomB())) {
                graph.setEndNode(node);
            }
        }
        for (Room room : dungeon.getRooms()) {
            Node node1 = graph.getNodeByName(room.getName());

            for (Room connectedRoom : room.getNextRooms().values()) {
                Node node2 = graph.getNodeByName(connectedRoom.getName());
                graph.addEdge(node1, node2);
            }
        }
    }

    public DungeonSoluce transform(GraphSoluce graphSoluce) {
        DungeonSoluce soluceDungeon = new DungeonSoluce();
        Node precedentN = graphSoluce.getSoluce().get(0);
        Room precedentR = mappedRoom(precedentN);


        for (Node node : graphSoluce.getSoluce()) {
            Room r = mappedRoom(node);
            if (node.equals(precedentN)) {
                continue; // on passe au for suivant
            }

            for(Map.Entry<Direction, Room> entree : precedentR.getNextRooms().entrySet()) {
                if(entree.getValue().equals(r)) {
                    soluceDungeon.addDirection(entree.getKey());
                    break;
                }
            }
            precedentN = node;
            precedentR = r;
        }

        return soluceDungeon;
    }

    public Node mappedNode(Room room) {
        return roomNode.get(room);
    }

    public Room mappedRoom(Node node) {
        for(Room r: roomNode.keySet()) {

            if(mappedNode(r).equals(node)) {
                return r;
            }
        }
        return null;
    }

}
