package algorithm;

import graph.Graph;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by grandarchtemplar on 13/12/16.
 * This code may work
 */
public class FloydWarshall {
    /**
     * Asymptotic: Θ(Θ(graph.adjacencyMatrix) * Vertex number) //in common is Θ(Θ(Vertex number)^3)
     * Can be used for ANY type of graph
     * SAVES: @graph
     * @param graph -- graph for calculation ways
     * @return matrix of distances where value of distance[i][j] is distance from i-vertex to j-vertex
     */
    @NotNull
    public static List<List<Integer>> allWays(@NotNull Graph graph) {
        List<List<Integer>> result = graph.adjacencyMatrix();
        int n = graph.verticesNum();
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    int t1 = result.get(i).get(k);
                    int t2 = result.get(k).get(j);
                    if (t1 < Integer.MAX_VALUE && t2 < Integer.MAX_VALUE) {
                        result.get(i).set(j, Math.min(t1 + t2, result.get(i).get(j)));
                    }
                }
            }
        }
        return result;
    }
}


