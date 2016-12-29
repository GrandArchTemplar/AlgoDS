package algorithm;

import com.sun.jmx.remote.internal.ArrayQueue;
import edge.Edge;
import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 22/11/16.
 * This code may work
 */
public class BFS {
    /**
     * IGNORES: weights
     * SAVES: Values of Vertex, saves @graph
     * Asymptotic: Θ(Vertex number + Edge number) if graph has adjacency list presentation
     * @param graph -- graph which consumed by this function to deal breadth first search
     * @param initNum -- number of start vertex
     * @return Vertex list with changed @distance field
     */
    @NotNull
    public static List<Vertex> distancingVertices(@NotNull Graph graph, int initNum) {
        //O(1)
        ArrayList<Vertex> result = new ArrayList<>(graph.getVertices());
        //O(V)
        result.forEach(v -> v.setDistance(Integer.MIN_VALUE));
        //O(1)
        result.get(initNum).setDistance(0);
        //O(1)
        int verticesCount = graph.verticesNum();
        //O(1)
        ArrayQueue<Vertex> vertices = new ArrayQueue<>(verticesCount);
        //O(1)
        ArrayList<Boolean> used = new ArrayList<>();
        //O(V)
        IntStream.range(0, verticesCount).forEach(i -> used.add(false));
        //O(1)
        used.set(initNum, true);
        //O(1)
        vertices.add(result.get(initNum));
        /*
         * In sum'll be no more than O(V + E) because in every vertex and edge search is only one time
         */
        while (!vertices.isEmpty()) {
            int from = vertices.remove(0).getNumber();
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

    /**
     * IGNORES: weights
     * SAVES: @graph
     * Asymptotic: Θ(Vertex number + Edge number) if graph has adjacency list presentation
     * @param graph -- graph which consumed by this function
     * @param initNum -- number of start vertex
     * @return list of distances where d[i] = distance from start vertex to i-vertex
     */
    @NotNull
    public static List<Integer> getDistances(@NotNull Graph graph, int initNum) {
        //O(V + E) + V = O(V + E)
        return distancingVertices(graph, initNum)
                .stream()
                .map(Vertex::getDistance)
                .collect(Collectors.toList());
    }

    /**
     * Asymptotic: Θ(Vertex number + Edge number) if graph has adjacency list presentation
     * SAVES: @graph
     * @param graph -- graph which consumed by this function
     * @param begin -- number of begin vertex
     * @param end -- number of end vertex
     * @return true if way from begin to end exists else return false
     */
    public static boolean isConnected(@NotNull Graph graph, int begin, int end) {
        int verticesCount = graph.verticesNum();
        ArrayList<Boolean> used = new ArrayList<>();
        IntStream.range(0, verticesCount).forEach(i -> used.add(false));
        used.set(begin, true);
        Queue<Vertex> vertices = new ArrayDeque<>(verticesCount);
        vertices.add(graph.getVertex(begin));
        while (!vertices.isEmpty()) {
            int from = vertices.remove().getNumber();
            graph.neighboursAsEdges(from)
                    .stream()
                    .map(Edge::getTo)
                    .filter(to -> !used.get(to))
                    .forEach(to -> {
                        used.set(to, true);
                        vertices.add(graph.getVertex(to));
                    });
        }
        return used.get(end);
    }
}
