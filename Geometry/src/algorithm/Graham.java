package algorithm;

import org.jetbrains.annotations.NotNull;
import point.Point;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static util.Utils.isCounterClockwise;
import static util.Utils.polarCorner;

/**
 * Created by grandarchtemplar on 27/11/16.
 * This code may work
 */
public class Graham {
    /**
     * Asymptotic: Θ(points number * log(points number))
     *
     * @param points list of points of some polygon
     * @return true if list of points create convex polygon else returns false
     */
    public static boolean isConvex(@NotNull List<@NotNull Point> points) {
        Point polar = points
                .stream()
                .min(Point::compareTo)
                .orElse(new Point(0, 0));
        ArrayList<Point> sortedPoints = new ArrayList<>();
        sortedPoints.add(polar);
        sortedPoints
                .addAll(points
                        .stream()
                        .skip(1)
                        .sorted(comparing(p -> polarCorner(polar, p)))
                        .collect(toCollection(ArrayList::new)));
        int n = sortedPoints.size();
        for (int i = 0; i < n; ++i) {
            Point p1 = sortedPoints.get(i % n);
            Point p2 = sortedPoints.get((i + 1) % n);
            Point p3 = sortedPoints.get((i + 2) % n);
            if (!isCounterClockwise(p1, p2, p3)) {
                return false;
            }
        }
        return true;
    }
}
