package additional;

import segmenttree.SegmentTree;

import java.util.ArrayList;

/**
 * Created by grandarchtemplar on 20/01/17.
 * This code may work
 */
public class Task5 {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(5);
        array.add(3);
        array.add(-2);
        array.add(0);
        array.add(-1);
        SegmentTree tree = new SegmentTree(array);
        System.out.println(tree.maximalSum(0, 4).getAnswer());
    }
}
