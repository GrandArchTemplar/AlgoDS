package edge;

/**
 * Created by grandarchtemplar on 14/11/16.
 */
public class Edge {
    private final int from;
    private final int to;
    private final int weight;


    public static Edge NEUTRAL = new Edge(0, 0, 0);

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return from == edge.from && to == edge.to && weight == edge.weight;

    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return Integer.toString(from) + " -> " + Integer.toString(to) + " with weight: " + Integer.toString(weight);
    }
}
