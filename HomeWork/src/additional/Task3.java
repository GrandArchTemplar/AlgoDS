package additional;

import algorithm.DFS;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;

import java.util.ArrayList;

/**
 * Created by grandarchtemplar on 19/01/17.
 * This code may work
 */
public class Task3 {
    public static void main(String[] args) {
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 0, 1));
        edges.add(new Edge(3, 4, 1));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(5, 3, 1));
        Graph graph = new AdjacencyList<>(edges);
        DFS.getCycle(graph).forEach(list -> {
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
