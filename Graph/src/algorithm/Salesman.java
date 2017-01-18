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
 *
 * This algorithm solve salesman tasks and give approximate answer which worse than optimal no more
 * than twice
 *
 * Algorithm:
 * 1. Find MST for graph
 * 2. Deal DFS bypass
 * 3. Retain only vertices which in special list
 * 4. For each pair find the shortest way with algorithm Dijkstra
 *
 * Asymptotic
 * 0. V -- Vertex num, E -- edge num, S -- special list size
 * 1. Θ((V + E) * log(E)) (Kruskal algorithm)
 * 2. Θ(V + E)
 * 3. Θ(V) because DFS bypass consist of Θ(V) vertices and find interception of two
 * collections takes Θ(C1 + C2) where C1, C2 -- collections sizes(if using hash set data structure)
 * 4. Θ(S * S * log(E)) because Dijkstra work for V log E ans V = S after third step
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
