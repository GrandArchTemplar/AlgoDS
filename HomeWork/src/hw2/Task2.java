package hw2;

import algorithm.Kruskal;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 25/11/16.
 * This code may work
 */
public class Task2 {
    public static void main(String[] args) {
        Graph graph;
        /*List<Edge> edges = new ArrayList<>();
        System.out.println("Enter edge count:");
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        System.out.println("Enter edges in format:\nfrom to weight");
        edges.clear();
        IntStream.range(0, n).forEach(i -> edges.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt())));
        graph = new AdjacencyList<>(edges);
        graph.undirected();
        Kruskal.getTree(graph).forEach(System.out::println);*/
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 0, 1));
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(2, 1, 2));
        edges.add(new Edge(2, 0, 3));
        /*edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(3, 2, 1));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(0, 2, 3));*/
        graph = new AdjacencyList<>(edges);
        Kruskal.getTree(graph).forEach(System.out::println);
    }
}
