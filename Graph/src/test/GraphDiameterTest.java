package test;

import algorithm.GraphDiameter;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import graph.Graphs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 24/11/16.
 * This code may work
 */
public class GraphDiameterTest {
    Graph graph;

    @Before
    public void setUp() {
        List<Edge> line = new ArrayList<>();
        IntStream
                .range(0, 25)
                .forEach(i -> line.add(new Edge(i, i + 1, 1)));
        graph = new AdjacencyList<>(line, Optional.empty());
        graph = Graphs.toUndirected(graph);
    }

    private static List<Edge> lineList(int count) {
        return IntStream
                .range(0, count)
                .boxed()
                .map(i -> new Edge(i, i + 1, 1))
                .collect(Collectors.toList());
    }

    public static void diameterTest(int num) {
        IntStream
                .range(1, num)
                .boxed()
                .map(GraphDiameterTest::lineList)
                .map(list -> new AdjacencyList<>(list))
                .map(Graphs::toUndirected)
                .forEach(graph -> Assert.assertEquals(GraphDiameter.getDiameter(graph), graph.verticesNum() - 1));
    }

    @Test
    public void lineParallelDiameterTest1() {
        IntStream.range(1, 10).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest1() {
        IntStream.range(1, 10).forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineParallelDiameterTest2() {
        IntStream.range(1, 50).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest2() {
        IntStream.range(1, 50).forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineParallelDiameterTest3() {
        IntStream.range(1, 100).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest3() {
        IntStream.range(1, 100).forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineParallelDiameterTest4() {
        IntStream.range(1, 200).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest4() {
        IntStream.range(1, 200).forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineParallelDiameterTest5() {
        IntStream.range(1, 300).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineParallelDiameterTest6() {
        IntStream.range(1, 400).boxed().parallel().forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest5() {
        IntStream.range(1, 300).forEach(GraphDiameterTest::diameterTest);
    }

    @Test
    public void lineDiameterTest6() {
        IntStream.range(1, 400).forEach(GraphDiameterTest::diameterTest);
    }

    public void eccentricityTest(int num) {
        IntStream
                .range(1, num)
                .boxed()
                .map(i -> i);
    }
}