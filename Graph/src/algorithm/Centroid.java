package algorithm;

import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;


/**
 * Created by grandarchtemplar on 13/12/16.
 * This code may work
 */
public class Centroid {
    /**
     * Asymptotic: Θ(Θ(graph.adjacencyMatrix) * Vertex number) //in common is Θ(Θ(Vertex number)^3)
     * Can be used for ANY type of graph
     * SAVES @graph
     * @param graph -- graph which consumed by this function
     * @return list of vertices which are in graph center
     */
    @NotNull
    public static List<Vertex> graphCenter(@NotNull Graph graph) {
        List<List<Integer>> d = FloydWarshall.allWays(graph);
        List<Integer> eccentricities = d.stream()
                .map(List::stream)
                .map(s -> s.max(Integer::compareTo).orElse(0))
                .collect(toCollection(ArrayList::new));
        int min = eccentricities.stream().min(Integer::compareTo).orElse(0);
        List<Vertex> result = new ArrayList<>();
        for (int i = 0; i < eccentricities.size(); ++i) {
            if (eccentricities.get(i) == min) {
                result.add(graph.getVertex(i));
            }
        }
        return result;
    }
}
