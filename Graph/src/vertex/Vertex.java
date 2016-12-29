package vertex;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Created by grandarchtemplar on 14/11/16.
 */
public class Vertex<V> {
    private final V value;
    private final int number;
    private int distance;

    public final static Vertex NEUTRAL = new Vertex<>(Optional.empty(), 0,0);

    public Vertex(@NotNull V value) {
        this.value = value;
        this.number = 0;
    }

    public Vertex(@NotNull V value, int number) {
        this.value = value;
        this.number = number;
    }

    public Vertex(@NotNull V value, int number, int distance) {
        this.value = value;
        this.number = number;
        this.distance = distance;
    }

    public int getNumber() {
        return number;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {

        this.distance = distance;
    }

    @NotNull
    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Number: " + number
                + " distance: " + distance
                + " value: " + value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        if (number != vertex.number) return false;
        return distance == vertex.distance && value.equals(vertex.value);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + number;
        result = 31 * result + distance;
        return result;
    }
}
