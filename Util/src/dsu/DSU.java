package dsu;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by grandarchtemplar on 23/11/16.
 * This code may work
 *
 * This is Disjoint-set-union structure with heuristics of tract compressions and forest of root trees
 * All operations works no more than log*
 *
 * R(v) -- rank of value
 * P(v) -- parent of value
 *
 * add'll work with O(1). It is trivial suggestion. Let's see union and find operations
 * Let's see n operations of union and m operations of find
 * m >= n because in union find calls two times. So we can suggest that
 * union(v1, v2) and union(find(v1), find(v2)) have equals asymptotic
 *
 * Lemma 1.
 * ------------------
 *| R(P(v)) >= R(v) |
 * ------------------
 * If v -- delegate so
 * R(P(v)) == R(v) because P(v) == v
 * else
 * R(L(v)) > R(v) (from union works principe)
 * where v -> L(v) -> L(L(v)) -> ... -> P(v) -- way from v to P(v)
 *
 * Lemma 2.
 *  ----------------------------------------------------------------
 * | count of ranks no more than log2(n) where n -- count of values |
 *  ----------------------------------------------------------------
 *  Let's take proof on induction.
 *  For n = 1 it's trivial proof
 *  For other we unite only two sets with equals rank. And we can't up rank more than high of correct binary tree
 *
 *  Th : Find works with log*(n)
 *  Let's split our vertices on 4 categories
 *  1. root
 *  2. child of root
 *  3. fast-grow calls R(P(v)) >= t^(R(v)) where t from e^(1/e) to 2
 *  4. slow-grow calls all other vertices
 *
 *  1 and 2 categories works for O(1)
 *
 *  For all 3 categories calls ranks grow up in exponential asymptotic so there are works for O(log*(n))
 *  because after O(log*(n)) calls rank of vertex'll be log*(n) so it'll be root
 *
 *  For 4 categories we have that count of this vertex'll be decrease in log after all calls so sum of
 *  log + log(log) + ... + log*(n) consist of log*(n) parts and it'll be no more than log*(n)
 *  So 4 categories vertices works for O(log*(n)) too
 *
 *  Our find works for O(log*(n))
 */
public class DSU<V> {
    private final HashMap<V, V> parent = new HashMap<>();
    private final HashMap<V, Integer> rank = new HashMap<>();

    public DSU() {

    }

    public void add(@NotNull V value) {
        parent.put(value, value);
        rank.put(value, 0);
    }

    @NotNull
    public V find(@NotNull V value) {
        V delegate = parent.get(value);
        if (value == delegate) {
            return value;
        } else {
            parent.put(value, find(delegate));
            return parent.get(value);
        }
    }

    public void union(@NotNull V value1, @NotNull V value2) {
        value1 = find(value1);
        value2 = find(value2);
        if (!value1.equals(value2)) {
            if (rank.get(value1) < rank.get(value2)) {
                V buffer = value1;
                value1 = value2;
                value2 = buffer;
            }
            parent.put(value2, value1);
            if (Objects.equals(rank.get(value1), rank.get(value2))) {
                rank.put(value1, rank.get(value1) + 1);
            }
        }
    }

    @Override
    public String toString() {
        return parent.values()
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}
