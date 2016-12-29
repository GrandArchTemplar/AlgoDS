package hw4;

import algorithm.Graham;
import point.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grandarchtemplar on 18/12/16.
 * This code may work
 */
public class Task1 {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        //Triangle
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));
        System.out.println("Triangle: " + Graham.isConvex(points));
        points.clear();
        //Line
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        System.out.println("Line: " + Graham.isConvex(points));
        points.clear();
        //Convex quadrangle
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        System.out.println("Convex quadrangle: " + Graham.isConvex(points));
        points.clear();
        //Non-convex quadrangle
        points.add(new Point(0, 0));
        points.add(new Point(4, 0));
        points.add(new Point(0, 4));
        points.add(new Point(1, 1));
        System.out.println("Non-convex quadrangle: " + Graham.isConvex(points));
        points.clear();
        //Non-convex quadrangle (because this is triangle)
        points.add(new Point(0, 0));
        points.add(new Point(4, 0));
        points.add(new Point(0, 4));
        points.add(new Point(2, 2));
        System.out.println("Non-convex quadrangle (because this is triangle): " + Graham.isConvex(points));
        points.clear();
    }
}
