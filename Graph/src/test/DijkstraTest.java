package test;


import algorithm.Dijkstra;
import algorithm.FloydWarshall;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;
import path.MinPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 27/11/16.
 * This code may work
 */
public class DijkstraTest {
    private Graph graph;
    @Before
    public void setUp() throws Exception {
        List<Edge> cycle = new ArrayList<>();
        cycle.add(new Edge(0, 1, 1));
        cycle.add(new Edge(0, 2, 1));
        cycle.add(new Edge(2, 3, 1));
        cycle.add(new Edge(3, 4, 1));
        cycle.add(new Edge(3, 5, 1));
        cycle.add(new Edge(1, 6, 1));
        cycle.add(new Edge(6, 7, 1));
        cycle.add(new Edge(6, 8, 1));
        List<Edge> line = new ArrayList<>();
        line.add(new Edge(0, 1, 2));
        line.add(new Edge(1, 2, 4));
        line.add(new Edge(2, 3, 5));
        line.add(new Edge(3, 4, 6));
        line.add(new Edge(4, 5, 8));
        line.add(new Edge(5, 6, 2));
        line.add(new Edge(6, 7, 3));
        line.add(new Edge(7, 8, 7));
        graph = new AdjacencyList<>(cycle);
        graph.undirected();
    }
    @Test
    public void test() throws Exception {
        setUp();
        MinPath mp = Dijkstra.distancingVertices(graph, 0);
        mp.getPath(4).forEach(System.out::println);
    }
}