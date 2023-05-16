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

public class Dungeon2Graph {

    Map<Room, Node> roomNode = new HashMap<>();
    public Graph graph = new Graph();

    public Dungeon2Graph(Dungeon dungeon) {
        for (Room room : dungeon.getRooms()) {
            Node node = new Node(room.getName(), room.getCoords());
            roomNode.put(room, node);
            graph.addNode(node);
        }
        for (Room room : dungeon.getRooms()) {
            for (Room nextRoom : room.getNextRooms().values()) {
                graph.addEdge(roomNode.get(room), roomNode.get(nextRoom));
            }
        }
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


    public DungeonSoluce transform(GraphSoluce soluceGraphBFS) {
        DungeonSoluce soluceDungeon = new DungeonSoluce();
        Node precedentN = soluceGraphBFS.getSoluce().get(0);
        Room precedentR = mappedRoom(precedentN);


        for (Node node : soluceGraphBFS.getSoluce()) {
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

}
