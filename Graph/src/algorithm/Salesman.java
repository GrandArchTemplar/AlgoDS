package algorithm;

import graph.AdjacencyList;
import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 19/12/16.
 * This code may work
 */
public class Salesman {
    @NotNull
    public static List<Vertex> solveWithMST(@NotNull Graph graph,
                                            @NotNull List<Integer> specialVertices) {
        HashSet<Integer> vertices = new HashSet<>(specialVertices);
        List<Vertex> way = DFS.byPass(new AdjacencyList<>(Kruskal.getTree(graph)), 0)
                .stream()
                .filter(v -> vertices.contains(v.getNumber()))
                .collect(Collectors.toList());
        int n = way.size();
        return IntStream.range(0, n)
                .boxed()
                .flatMap(i -> Dijkstra
                        .distancingVertices(graph, way.get(i).getNumber())
                        .getPath(way.get((i + 1) % n).getNumber())
                        .stream())
                .collect(Collectors.toList());
    }
}
