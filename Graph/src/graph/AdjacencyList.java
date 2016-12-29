package graph;

import edge.Edge;
import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by grandarchtemplar on 14/11/16.
 * Oriented
 * graph
 */
public class AdjacencyList<V> implements Graph {
    private List<Vertex<V>> vertices;
    private List<ArrayList<Edge>> adjacency;
    private int verticesNum;
    private int edgeNum;

    /**
     * Can consume repeatable edges
     * Not support many-edge ways in graph
     * Construct graph for O(E + V)
     */
    public AdjacencyList(@NotNull List<Edge> edges, @NotNull V defaultValue) {
        //O(1)
        this.edgeNum = edges.size();
        //O(E)
        this.verticesNum = edges
                .stream()
                .map(e -> Math.max(e.getFrom(), e.getTo()))
                .max(Integer::compare).orElse(-1) + 1;
        this.vertices = new ArrayList<>();
        this.adjacency = new ArrayList<>();
        //O(V)
        IntStream
                .range(0, this.verticesNum)
                .forEach(i -> {
                    this.vertices.add(new Vertex<>(defaultValue, i));
                    this.adjacency.add(new ArrayList<>());
                });
        //O(E)
        edges
                .stream()
                .distinct()
                .forEach(e -> this.adjacency.get(e.getFrom()).add(e));
    }

    //O(V + E)
    public AdjacencyList(@NotNull List<Edge> edges) {
        AdjacencyList<V> graph = new AdjacencyList<>(edges, (V) Optional.empty());
        this.vertices = graph.vertices;
        this.adjacency = graph.adjacency;
        this.edgeNum = graph.edgeNum;
        this.verticesNum = graph.verticesNum;
    }

    //O(1)
    @Override
    public int verticesNum() {
        return verticesNum;
    }

    //O(1)
    @Override
    public int edgeNum() {
        return edgeNum;
    }

    //O(V)
    @NotNull
    @Override
    public List<Vertex> getVertices() {
        return vertices.stream().collect(toList());
    }

    //O(E)
    @NotNull
    @Override
    public List<Edge> neighboursAsEdges(int num) {
        return adjacency.get(num).stream().collect(toList());
    }

    //O(V + E)
    @NotNull
    @Override
    public List<Edge> allEdges() {
        return IntStream
                //O(V)
                .range(0, verticesNum)
                .boxed()
                //after this action there are no more than E edges'll be returned so O(E)
                .map(this::neighboursAsEdges)
                .flatMap(List::stream)
                .collect(toList());
    }

    //O(V + E)
    @Override
    public void undirected() {
        //Call constructor with O(V + E)
        AdjacencyList<V> graph = new AdjacencyList<V>(Stream
                .concat(this.allEdges()
                                .stream()
                                .map(e -> new Edge(e.getTo(), e.getFrom(), e.getWeight())),
                        this.allEdges()
                                .stream())
                .collect(toList()),
                (V) Optional.empty());
        this.vertices = graph.vertices;
        this.adjacency = graph.adjacency;
        this.edgeNum = graph.edgeNum;
        this.verticesNum = graph.verticesNum;
    }

    //O(1)
    @Override
    @NotNull
    public Vertex getVertex(int num) {
        return vertices.get(num);
    }

    //O(V^2)
    @Override
    @NotNull
    public List<List<Integer>> adjacencyMatrix() {
        List<List<Integer>> matrix = IntStream
                .range(0, verticesNum)
                .boxed()
                .map(i -> IntStream
                        .range(0, verticesNum)
                        .map(j -> i == j ? 0 : Integer.MAX_VALUE)
                        .boxed()
                        .collect(toCollection(ArrayList::new)))
                .collect(toCollection(ArrayList::new));
        allEdges().forEach(e -> matrix.get(e.getFrom()).set(e.getTo(), e.getWeight()));
        return matrix;
    }
}
