package test;

import algorithm.Salesman;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 29/12/16.
 * This code may work
 */
public class SalesmanTest {
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(3, 0, 1));
        graph = new AdjacencyList<>(edges);
        graph.undirected();
    }

    @Test
    public void simpleTest() throws Exception {
        Salesman.solveWithMST(graph, IntStream.range(0, 2).boxed().collect(Collectors.toList()))
                .forEach(System.out::println);
    }

}