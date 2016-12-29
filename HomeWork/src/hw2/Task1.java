package hw2;

import algorithm.GraphDiameter;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 25/11/16.
 * This code may work
 */
public class Task1 {
    public static void main(String[] args) {
        Graph graph;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(2, 6, 1));
        edges.add(new Edge(2, 1, 1));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(3, 4, 1));
        edges.add(new Edge(3, 9, 1));
        edges.add(new Edge(3, 11, 1));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(11, 12, 1));
        edges.add(new Edge(11, 13, 1));
        edges.add(new Edge(12, 14, 1));
        edges.add(new Edge(12, 15, 1));
        edges.add(new Edge(9, 7, 1));
        edges.add(new Edge(9, 8, 1));
        edges.add(new Edge(9, 10, 1));
        graph = new AdjacencyList<>(edges);
        graph.undirected();
        System.out.println("Diameter of graph is " + GraphDiameter.getDiameter(graph));
        System.out.println("Eccentricity of graph in vertex number 2 is "
                + GraphDiameter.getEccentricity(graph, 2));
    }
}
