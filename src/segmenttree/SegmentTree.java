package segmenttree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static segmenttree.Node.mix;

/**
 * Created by grandarchtemplar on 20/01/17.
 * This code may work
 */
public class SegmentTree {
    List<Node> tree = new ArrayList<>();
    int size;

    public SegmentTree(ArrayList<Integer> initArray) {
        this.size = initArray.size();
        IntStream.range(0, 4 * this.size).forEach(i -> tree.add(new Node()));
        build(initArray, 1, 0, initArray.size() - 1);
    }

    private void build(ArrayList<Integer> initArray, int v, int treeLeft, int treeRight) {
        if (treeLeft == treeRight) {
            tree.set(v, new Node(initArray.get(treeLeft)));
        } else {
            int treeMid = (treeLeft + treeRight) / 2;
            build(initArray, v * 2, treeLeft, treeMid);
            build(initArray, v * 2 + 1, treeMid + 1, treeRight);
            tree.set(v, mix(tree.get(v * 2), tree.get(v * 2 + 1)));
        }
    }

    private void update(int v, int treeLeft, int treeRight, int position, int newValue) {
        if (treeLeft == treeRight) {
            tree.set(v, new Node(newValue));
        } else {
            int treeMid = (treeLeft + treeRight) / 2;
            if (position <= treeMid) {
                update(v * 2, treeLeft, treeMid, position, newValue);
            } else {
                update(v * 2 + 1, treeMid + 1, treeRight, position, newValue);
            }
            tree.set(v, mix(tree.get(v * 2), tree.get(v * 2 + 1)));
        }
    }

    private Node query(int v, int treeLeft, int treeRight, int left, int right) {
        if (left == treeLeft && treeRight == right) {
            return tree.get(v);
        }
        int treeMid = (treeLeft + treeRight) / 2;
        if (right <= treeMid) {
            return query(v * 2, treeLeft, treeMid, left, right);
        }
        if (left > treeMid) {
            return query(v * 2 + 1, treeMid + 1, treeRight, left, right);
        }
        return mix(
                query(v * 2, treeLeft, treeMid, left, treeMid),
                query(v * 2 + 1, treeMid + 1, treeRight, treeMid + 1, right)
        );
    }

    private Node query(int v, int treeLeft, int treeRight, int left, int right, int result) {
        int treeMid = (treeLeft + treeRight) / 2;
        if (right <= treeMid) {
            return query(v * 2, treeLeft, treeMid, left, right);
        }
        if (left > treeMid) {
            return query(v * 2 + 1, treeMid + 1, treeRight, left, right);
        }
        if (left == treeLeft && treeRight == right) {
            return tree.get(v);
        }
        return mix(
                query(v * 2, treeLeft, treeMid, left, treeMid),
                query(v * 2 + 1, treeMid + 1, treeRight, treeMid + 1, right)
        );
    }

    private int maximalSumInner(int left, int right) {
        return query(1, 0, size - 1, left, right).getAnswer();
    }


    public Node maximalSum(int left, int right) {
        return query(1, 0, size - 1, left, right, maximalSumInner(left, right));
    }
}
