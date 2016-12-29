package algorithm;

import dsu.DSU;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static algorithm.BFS.isConnected;
import static algorithm.Dijkstra.distancingVertices;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

/**
 * Created by grandarchtemplar on 24/11/16.
 * This code may work
 */
public class Kruskal {
    /**
     * Asymptotic: Θ(Edge number * log(Edge number)
     * + Vertex number * inverse Akkerman's function(Vertex num))
     * @param graph -- graph for calculates
     * @return list of edge which from minimum spanning tree of this @graph graph
     */
    @NotNull
    public static List<Edge> getTree(@NotNull Graph graph) {
        DSU<Integer> forest = new DSU<>();
        IntStream.range(0, graph.verticesNum()).forEach(forest::add);
        return graph.allEdges()
                .stream()
                //O(E log E) = O(E log V^2) = O(2E log V) = O(E log V)
                .sorted(comparing(Edge::getWeight))
                .map(e -> {
                    int from = e.getFrom();
                    int to = e.getTo();
                    /*
                     * O(f(V)) where f -- inverse Akkerman's function. while V < 10^600, f(V) < 5
                     * so we can think that
                     * O(f(V)) ~= O(const) ~= O(1)
                     * //I can't prove this asympotic but I can prove that this asymptotic
                     * is O(log*(V)) where log* -- iteration logarithm.
                     * log*(n) = log(log(log...(log(n)..))
                     * logarithm takes while result'll be less then 1
                     * log* it is inverse function of arrow notation of Knut, so we can think
                     * that O(log*(n)) ~= O(1)
                     * full prove about log* can be find in DSU class
                     */
                    if (!forest.find(from).equals(forest.find(to))) {
                        forest.union(from, to);
                        return e;
                    } else {
                        return Edge.NEUTRAL;
                    }
                })
                .filter(e -> !e.equals(Edge.NEUTRAL))
                .collect(toList());
    }

    /**
     * Asymptotic: Θ((Vertex number + Edge number) * log(Edge number))
     * @param graph -- graph for calculating
     * @param begin -- number of begin vertex
     * @param end -- number of end vertex
     * @return list of vertex from minimal way from @begin to @end vertices with maximal minimal edge
     */
    @NotNull
    public static List<Vertex> minMaxWay(@NotNull Graph graph, int begin, int end) {
        List<Edge> edges = graph.allEdges();
        if (!isConnected(graph, begin, end)) {
            return new ArrayList<>();
        }
        edges.sort((e1, e2) -> e2.getWeight() - e1.getWeight());
        int left = 0;
        int right = graph.edgeNum();
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (isConnected(
                    new AdjacencyList<>(edges
                            .stream()
                            .limit(mid)
                            .collect(toList())),
                    begin,
                    end)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return distancingVertices(
                new AdjacencyList<>(edges
                        .stream()
                        .limit(right)
                        .collect(toList())),
                begin)
                .getPath(end);
    }
}
