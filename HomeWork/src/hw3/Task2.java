package hw3;

import algorithm.Kruskal;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 18/12/16.
 * This code may work
 */
public class Task2 {
    public static void main(String[] args) {
        Graph graph;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 6, 10));
        edges.add(new Edge(6, 2, 10));
        edges.add(new Edge(0, 3, 3));
        edges.add(new Edge(3, 2, 100));
        edges.add(new Edge(0, 4, 4));
        edges.add(new Edge(4, 5, 4));
        edges.add(new Edge(5, 2, 4));
        graph = new AdjacencyList<>(edges);
        Kruskal.minMaxWay(graph, 0, 2).stream().map(Vertex::getNumber).forEach(System.out::println);
        System.out.println(graph.getVertex(2).getNumber());
    }
}
