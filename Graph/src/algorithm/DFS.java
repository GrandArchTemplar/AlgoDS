package algorithm;

import edge.Edge;
import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * Created by grandarchtemplar on 18/12/16.
 * This code may work
 */
public class DFS {
    @NotNull
    public static List<Vertex> distancingVertices(@NotNull Graph graph, int initNum) {
        ArrayList<Vertex> result = new ArrayList<>(graph.getVertices());
        result.forEach(v -> v.setDistance(Integer.MIN_VALUE));
        result.get(initNum).setDistance(0);
        int verticesCount = graph.verticesNum();
        Stack<Vertex> vertices = new Stack<>();
        ArrayList<Boolean> used = new ArrayList<>();
        IntStream.range(0, verticesCount).forEach(i -> used.add(false));
        used.set(initNum, true);
        vertices.push(result.get(initNum));
        while (!vertices.isEmpty()) {
            int from = vertices.pop().getNumber();
            graph
                    .neighboursAsEdges(from)
                    .stream()
                    .map(Edge::getTo)
                    .filter(to -> !used.get(to))
                    .forEach(to -> {
                        used.set(to, true);
                        vertices.add(result.get(to));
                        result.get(to).setDistance(result.get(from).getDistance() + 1);
                    });
        }
        return result;
    }

    @NotNull
    public static List<Vertex> byPass(@NotNull Graph graph, int initNum) {
        ArrayList<Vertex> result = new ArrayList<>(graph.getVertices());
        final int[] order = {1};
        result.forEach(v -> v.setDistance(Integer.MIN_VALUE));
        result.get(initNum).setDistance(0);
        int verticesCount = graph.verticesNum();
        Stack<Vertex> vertices = new Stack<>();
        ArrayList<Boolean> used = new ArrayList<>();
        IntStream.range(0, verticesCount).forEach(i -> used.add(false));
        used.set(initNum, true);
        vertices.push(result.get(initNum));
        while (!vertices.isEmpty()) {
            int from = vertices.pop().getNumber();
            graph
                    .neighboursAsEdges(from)
                    .stream()
                    .map(Edge::getTo)
                    .filter(to -> !used.get(to))
                    .forEach(to -> {
                        used.set(to, true);
                        vertices.add(result.get(to));
                        result.get(to).setDistance(order[0]++);
                    });
        }
        return result
                .stream()
                .sorted(comparing(Vertex::getDistance))
                .collect(toList());
    }
}
