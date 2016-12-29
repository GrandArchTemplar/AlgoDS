package graph;

import edge.Edge;
import vertex.Vertex;

import java.util.List;

/**
 * Created by grandarchtemplar on 22/11/16.
 * This code may work
 */
public interface Graph {
    int verticesNum();
    int edgeNum();
    List<Vertex> getVertices();
    List<Edge> neighboursAsEdges(int num);
    List<Edge> allEdges();
    void undirected();
    Vertex getVertex(int num);
    List<List<Integer>> adjacencyMatrix();
}
