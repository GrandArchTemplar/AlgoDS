package hw4;

import algorithm.Salesman;
import edge.Edge;
import graph.AdjacencyList;
import graph.Graph;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by grandarchtemplar on 29/12/16.
 * This code may work
 */
public class Task2 {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();
        Random gen = new Random();
        int n, m;
        System.out.println("Please input horizontal and vertical sizes of country");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        for (int i  = 0; i < n; ++i) {
            for (int j = i * m ; j < i * m + m - 1; ++j) {
                roads.add(new Edge(j, j + 1, gen.nextInt(300) + 1));
            }
        }
        for (int i  = 0; i < n - 1; ++i) {
            for (int j = i * m ; j < i * m + m; ++j) {
                roads.add(new Edge(j, j + m, gen.nextInt(300) + 1));
            }
        }
        List<Integer> specialVertices = IntStream.range(0, n * m / 2)
                .map(i -> gen.nextInt(n * m))
                .distinct()
                .sorted()
                .boxed()
                .collect(toList());
        Graph graph = new AdjacencyList<>(roads);
        graph.undirected();
        System.out.println("ROADS:");
        roads.forEach(System.out::println);
        System.out.println("CHOOSEN VERTICES:");
        specialVertices.forEach(i -> System.out.print(i + " "));
        System.out.println();
        System.out.println("ANSWER:");
        Salesman.solveWithMST(graph, specialVertices)
                .stream()
                .map(Vertex::getNumber)
                .forEach(i -> System.out.print(i + " "));
    }
}
