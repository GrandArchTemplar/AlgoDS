package test;

import algorithm.BFS;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;
import util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 22/11/16.
 * This code may work
 */
public class BFSTest {
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
        //cycle.add(new Edge(8, 9, 1));
        List<Edge> hardCycle = new ArrayList<>();
        hardCycle.add(new Edge(0, 5, 1));
        hardCycle.add(new Edge(1, 2, 1));
        hardCycle.add(new Edge(2, 3, 1));
        hardCycle.add(new Edge(3, 2, 1));
        hardCycle.add(new Edge(4, 2, 1));
        hardCycle.add(new Edge(5, 4, 1));
        hardCycle.add(new Edge(6, 1, 1));
        hardCycle.add(new Edge(7, 4, 1));
        graph = new AdjacencyList<>(cycle);
    }

    @Test
    public void mainTest() throws Exception {
        setUp();
        BFS.distancingVertices(graph, 0).forEach(System.out::println);
        Integer a = 1;
        Integer b = 5;
        System.out.println(a + " " + b);
        Util.swap(a, b);
        System.out.println(a + " " + b);
    }
}