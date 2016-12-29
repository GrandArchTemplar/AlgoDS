package algorithm;

import graph.Graph;
import org.jetbrains.annotations.NotNull;
import path.MinPath;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by grandarchtemplar on 27/11/16.
 * This code may work
 */
public class Dijkstra {
    /**
     * Asymptotic: O(Edge num * log(Edge num)) if graph has adjacency list presentation
     * SAVES: @graph
     * Can be used for ANY type of graph
     * @param graph -- current graph
     * @param initNum -- number of start vertex
     * @return MinPath class for this graph and this initNum
     */
    @NotNull
    public static MinPath distancingVertices(@NotNull Graph graph, int initNum) {
        ArrayList<Vertex> result = new ArrayList<>(graph.getVertices());
        HashMap<Integer, Vertex> parent = new HashMap<>();
        result.forEach(v -> v.setDistance(Integer.MAX_VALUE));
        result.get(initNum).setDistance(0);
        TreeSet<Vertex> queue = new TreeSet<>(
                (v1, v2) -> {
                    int d1 = v1.getDistance();
                    int d2 = v2.getDistance();
                    return d1 != d2 ? d1 - d2 : v1.getNumber() - v2.getNumber();
                }
        );
        queue.add(result.get(initNum));
        while (!queue.isEmpty()) {
            Vertex from = queue.pollFirst();
            int fromNumber = from.getNumber();
            graph.neighboursAsEdges(fromNumber)
                    .stream()
                    .filter(e -> result.get(fromNumber).getDistance() + e.getWeight()
                            < result.get(e.getTo()).getDistance())
                    .forEach(e -> {
                        Vertex to = result.get(e.getTo());
                        parent.put(to.getNumber(), from);
                        to.setDistance(result.get(fromNumber).getDistance()
                                + e.getWeight());
                        result.set(e.getTo(), to);
                        queue.add(to);
                    });
        }
        return new MinPath(graph.getVertex(initNum), result, parent);
    }
}
