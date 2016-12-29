package util;

import org.jetbrains.annotations.NotNull;
import point.Point;

/**
 * Created by grandarchtemplar on 26/11/16.
 * This code may work
 */
public class Utils {
    public static double polarCorner(@NotNull Point begin, @NotNull Point end) {
        int effectiveX = end.getX() - begin.getX();
        int effectiveY = end.getY() - begin.getY();
        return Math.atan2(effectiveY, effectiveX);
    }

    public static boolean isCounterClockwise(@NotNull Point p1, @NotNull Point p2, @NotNull Point p3) {
        return p1.getX() * (p2.getY() - p3.getY())
                - p2.getX() * (p1.getY() - p3.getY())
                + p3.getX() * (p1.getY() - p2.getY()) > 0;
    }
}
