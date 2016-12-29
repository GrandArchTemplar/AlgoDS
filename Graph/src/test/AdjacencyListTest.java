package test;

import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by grandarchtemplar on 25/11/16.
 * This code may work
 */
public class AdjacencyListTest {
    Graph graph;
    @Before
    public void setUp() throws Exception {
        List<Edge> cycle = new ArrayList<>();
        cycle.add(new Edge(0, 1, 1));
        cycle.add(new Edge(0, 2, 2));
        cycle.add(new Edge(2, 3, 3));
        cycle.add(new Edge(3, 4, 4));
        cycle.add(new Edge(3, 5, 5));
        cycle.add(new Edge(1, 6, 6));
        cycle.add(new Edge(6, 7, 7));
        cycle.add(new Edge(6, 8, 9));
        graph = new AdjacencyList<>(cycle, Optional.empty());
        graph.allEdges();
    }

    @Test
    public void test() throws Exception {
        setUp();
        System.out.println(graph.allEdges().size());
    }

}