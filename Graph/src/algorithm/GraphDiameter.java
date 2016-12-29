package algorithm;

import graph.Graph;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.Comparator;
import java.util.stream.IntStream;

import static algorithm.BFS.distancingVertices;
import static algorithm.BFS.getDistances;

/**
 * Created by grandarchtemplar on 23/11/16.
 * This code may work
 */
public class GraphDiameter {
    /**
     * Asymptotic: Θ(Vertex number + Edge number)
     * WARNING: This method can be used only for acycling unweighted graph if wanted ok answer
     * @param graph -- graph for calculating it diameter
     * @return diameter of @graph
     */
    @Deprecated
    public static int getDiameterWrong(@NotNull Graph graph) {
        return getDistances(graph, distancingVertices(graph, 0)
                .stream()
                .max(Comparator.comparing(Vertex::getDistance))
                .orElse(Vertex.NEUTRAL)
                .getNumber()
        )
                .stream()
                .max(Integer::compare)
                .orElse(0);
    }


    /**
     * Asymptotic Θ(Vertex number * (Vertex number + Edge number))
     * Can be used only for unweighted graph
     * @param graph -- graph for calculating it diameter
     * @return diameter of graph
     */
    public static int getDiameter(@NotNull Graph graph) {
        return IntStream
                .range(0, graph.verticesNum())
                .boxed()
                .flatMap(i -> getDistances(graph, i).stream())
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Asymptotic: Θ(Vertex number + Edge number)
     * Can be used only for unweighted graph
     * @param graph -- graph for calculating
     * @param num -- number of vertex
     * @return eccentricity of @num vertex in @graph graph
     */
    public static int getEccentricity(@NotNull Graph graph, int num) {
        return distancingVertices(graph, num)
                .stream()
                .max(Comparator.comparing(Vertex::getDistance))
                .orElse(Vertex.NEUTRAL)
                .getDistance();
    }
}
