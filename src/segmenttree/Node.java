package segmenttree;

import com.sun.istack.internal.NotNull;

import static java.lang.Math.max;

/**
 * Created by grandarchtemplar on 20/01/17.
 * This code may work
 * <p>
 * Code taken from http://e-maxx.ru/algo/segment_tree
 * Let's keep in node of tree sum, prefix sum and suffix sum
 * Sum -- sum of this interval
 * suffix sum -- max sum of [x, end)
 * prefix sum -- max sum of [begin, x)
 * Answer is max(sum, prefix sum, suffix sum)
 *
 * Building:
 * this.ans = leftSon means [a, b) = [a, (a + b) / 2)
 * this.ans = rightSon means [a, b) = [(a + b) / 2 + 1, b)
 * this.ans = leftSon.suffix + rightSon.prefix means [a, b) = [a, end) + [begin, b)
 * This node used for SegmentTree so all operations get only log n
 */
public class Node {
    private int sum;
    private int prefix;
    private int suffix;
    private int answer;

    @NotNull
    public static Node mix(@NotNull Node left, @NotNull Node right) {
        Node result = new Node();
        result.sum = left.sum + right.sum;
        result.prefix = max(left.prefix, left.sum + right.prefix);
        result.suffix = max(left.suffix, right.sum + right.suffix);
        result.answer = max(left.suffix + right.prefix, max(left.answer, right.answer));
        return result;
    }

    public Node() {
        this.sum = 0;
        this.prefix = 0;
        this.suffix = 0;
        this.answer = 0;
    }

    public Node(int value) {
        this.sum = value;
        this.prefix = this.suffix = this.answer = max(0, value);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
