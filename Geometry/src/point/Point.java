package point;

import org.jetbrains.annotations.NotNull;

/**
 * Created by grandarchtemplar on 26/11/16.
 * This code may work
 */
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(@NotNull Point o) {
        return this.x == o.x ? this.y - o.y : this.x - o.x;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
