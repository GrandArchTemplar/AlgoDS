package algorithm;

import edge.Edge;
import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

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

    /**
     * consume directed graph only
     * @param graph -- graph
     * @return list of vertex list where each list is cycle
     */
    public static List<List<Vertex>> getCycle(Graph graph) {
        int n = graph.verticesNum();
        ArrayList<ArrayList<Vertex>> results = new ArrayList<>();
        HashSet<Vertex> remaining = new HashSet<>(graph.getVertices());
        ArrayList<Vertex> parents = new ArrayList<>();
        IntStream.range(0, n).forEach(i -> parents.add(Vertex.NEUTRAL));
        Stack<Vertex> vertices = new Stack<>();
        ArrayList<Vertex> result = new ArrayList<>();
        while (!remaining.isEmpty()) {
            Vertex init = remaining.stream().findAny().orElse(Vertex.NEUTRAL);
            vertices.add(init);
            while (!vertices.isEmpty()) {
                Vertex from = vertices.pop();
                int fromNumber = from.getNumber();
                remaining.remove(from);
                graph.neighboursAsEdges(fromNumber)
                        .stream()
                        .map(Edge::getTo)
                        .map(graph::getVertex)
                        .forEach(to -> {
                            if (!remaining.contains(to)) {
                                Vertex current = from;
                                Vertex point = to;
                                result.clear();
                                while (!current.equals(point)) {
                                    result.add(current);
                                    current = parents.get(current.getNumber());
                                }
                                result.add(point);
                                Collections.reverse(result);
                                result.add(point);
                                results.add(new ArrayList<>(result));
                                return;
                            }
                            vertices.add(to);
                            remaining.remove(to);
                            parents.set(to.getNumber(), from);
                        });
            }

        }
        return results.stream().collect(toList());

    }
}
