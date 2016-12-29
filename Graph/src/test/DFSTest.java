package test;

import algorithm.DFS;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 18/12/16.
 * This code may work
 */
public class DFSTest {
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 2, 10));
        edges.add(new Edge(2, 6, 2));
        edges.add(new Edge(2, 1, 3));
        edges.add(new Edge(2, 0, 4));
        edges.add(new Edge(6, 3, 4));
        edges.add(new Edge(5, 2, 21));
        edges.add(new Edge(5, 8, 21));
        edges.add(new Edge(5, 9, 21));
        edges.add(new Edge(5, 4, 21));
        edges.add(new Edge(5, 11, 21));
        edges.add(new Edge(3, 8, 4));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 15));
        edges.add(new Edge(3, 9, 16));
        edges.add(new Edge(3, 11, 17));
        edges.add(new Edge(4, 5, 8));
        edges.add(new Edge(11, 12, 9));
        edges.add(new Edge(11, 13, 10));
        edges.add(new Edge(12, 14, 11));
        edges.add(new Edge(12, 15, 12));
        edges.add(new Edge(9, 7, 13));
        edges.add(new Edge(9, 8, 14));
        edges.add(new Edge(9, 10, 15));
        graph = new AdjacencyList<>(edges);
        //graph.undirected();
    }

    @Test
    public void testDistanting() throws Exception {
        DFS.distancingVertices(graph, 0).forEach(System.out::println);
        System.out.println();
        DFS.byPass(graph, 0).forEach(System.out::println);
    }
}
