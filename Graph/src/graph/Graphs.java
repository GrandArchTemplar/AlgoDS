package graph;

import edge.Edge;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by grandarchtemplar on 23/11/16.
 * This code may work
 */
public class Graphs {
    //O(V + E)
    @NotNull
    public static Graph toUndirected(@NotNull Graph graph) {
        //Call constructor with 2E edges and V vertices so asymptotic'll be O(V + 2E) = O(V + E)
        return new AdjacencyList<>(Stream
                .concat(graph.allEdges()
                                .stream()
                                .map(e -> new Edge(e.getTo(), e.getFrom(), e.getWeight())),
                        graph.allEdges()
                                .stream())
                .collect(Collectors.toList()),
                Optional.empty());
    }
}
