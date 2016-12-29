package test;

import algorithm.Centroid;
import algorithm.FloydWarshall;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by grandarchtemplar on 13/12/16.
 * This code may work
 */
public class FloydWarshallTest {
    private Graph graph;
    @Before
    public void setUp() throws Exception {
        List<Edge> cycle = new ArrayList<>();
        cycle.add(new Edge(0, 1, 1));
        cycle.add(new Edge(0, 2, 1));
        cycle.add(new Edge(2, 3, 5));
        cycle.add(new Edge(3, 4, 1));
        cycle.add(new Edge(3, 5, 1));
        cycle.add(new Edge(1, 6, 1));
        cycle.add(new Edge(6, 7, 1));
        cycle.add(new Edge(6, 8, 1));
        cycle.add(new Edge(6, 0, 1));
        cycle.add(new Edge(6, 2, 1));
        cycle.add(new Edge(6, 3, 1));
        List<Edge> line = new ArrayList<>();
        line.add(new Edge(0, 1, 2));
        line.add(new Edge(1, 2, 4));
        line.add(new Edge(2, 3, 5));
        line.add(new Edge(3, 4, 5));
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
        FloydWarshall.allWays(graph).forEach(l -> {
            l.forEach(e -> System.out.printf("%3d", e));
            System.out.println();
        });
        Centroid.graphCenter(graph).forEach(System.out::println);
    }

}