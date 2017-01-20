package additional;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

/**
 * Created by grandarchtemplar on 19/01/17.
 * This code may work
 * <p>
 * Modification of merge sort with counter and counting length of parts array which
 * Θ(n log n) where n -- size
 * for all i in [a, b] l[i] > r[i]
 */
public class Task1 {
    private static int merge(ArrayList<Integer> arr, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < left.size() || j < right.size()) {
            if (i == left.size()) {
                arr.set(i + j, right.get(j));
                ++j;
            } else if (j == right.size()) {
                arr.set(i + j, left.get(i));
                ++i;
            } else if (right.get(j) > left.get(i)) {
                arr.set(i + j, left.get(i));
                ++i;
            } else {
                arr.set(i + j, right.get(j));
                count += left.size() - i;
                ++j;
            }
        }
        return count;
    }

    private static int solver(ArrayList<Integer> arr) {
        if (arr.size() < 2)
            return 0;

        int m = (arr.size() + 1) / 2;
        ArrayList<Integer> left = arr.stream().limit(m).collect(toCollection(ArrayList::new));
        ArrayList<Integer> right = arr.stream().skip(m).collect(toCollection(ArrayList::new));

        return solver(left) + solver(right) + merge(arr, left, right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(0);
        array.add(1);
        array.add(2);
        array.add(4);
        array.add(3);
        System.out.println(solver(array));
    }
}
