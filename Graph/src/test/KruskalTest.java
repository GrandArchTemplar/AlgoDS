package test;

import algorithm.Kruskal;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 25/11/16.
 * This code may work
 */
public class KruskalTest {

    private Graph graph, graph1;
    private Random generator = new Random();

    @Before
    public void setUp() throws Exception {
        List<Edge> cycle = new ArrayList<>();
        cycle.add(new Edge(0, 1, 1));
        cycle.add(new Edge(0, 2, 2));
        cycle.add(new Edge(2, 3, 3));
        cycle.add(new Edge(3, 4, 4));
        cycle.add(new Edge(3, 5, 5));
        cycle.add(new Edge(1, 6, 6));
        cycle.add(new Edge(6, 7, 4));
        cycle.add(new Edge(6, 2, 8));
        graph = new AdjacencyList<>(cycle);
        IntStream.range(7, 100).forEach(i -> cycle.add(new Edge(i, i + 1, generator.nextInt(6))));
        graph1 = new AdjacencyList<>(cycle);
    }

    @Test
    public void kruskalSimpleTest() throws Exception {
        setUp();
        Kruskal.getTree(graph).forEach(System.out::println);
        System.out.println();
        Kruskal.getTree(graph1).forEach(System.out::println);
    }

    @Test
    public void minMaxWayTest() throws Exception {
        setUp();
        Kruskal.minMaxWay(graph, 3, 0).forEach(System.out::println);
    }

}