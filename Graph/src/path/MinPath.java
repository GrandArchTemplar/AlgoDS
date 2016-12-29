package path;

import org.jetbrains.annotations.NotNull;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by grandarchtemplar on 13/12/16.
 * This code may work
 */
public class MinPath {
    private final Vertex initVertex;
    private final ArrayList<Vertex> distances;
    private final HashMap<Integer, Vertex> parents;

    public MinPath(@NotNull Vertex initVertex,
                   @NotNull ArrayList<Vertex> distances,
                   @NotNull HashMap<Integer, Vertex> parents) {
        this.initVertex = initVertex;
        this.distances = distances;
        this.parents = parents;
    }

    @NotNull
    public Vertex getInitVertex() {
        return initVertex;
    }

    @NotNull
    public List<Vertex> getPath(int to) {
        List<Vertex> path = new ArrayList<>();
        Vertex currentVertex = parents.get(to);
        while(!currentVertex.equals(initVertex)) {
            path.add(currentVertex);
            currentVertex = parents.get(currentVertex.getNumber());
        }
        path.add(initVertex);
        Collections.reverse(path);
        return path;
    }
}
