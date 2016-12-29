package test;

import dsu.DSU;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by grandarchtemplar on 25/11/16.
 * This code may work
 */
public class DSUTest {
    private DSU<Integer> dsu;

    @Test
    public void DSUTest() throws Exception {
        dsu = new DSU<>();
        IntStream.range(0, 10).forEach(dsu::add);
        dsu.union(5, 4);
        dsu.union(5, 5);
        System.out.println(dsu.find(4));
    }
}