package algo.tree;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.lang.Math;


/**
 * Code to check if a binary tree is balanced
 * see https://algo.monster/problems/balanced_binary_tree
 */
public class BalancedBinary {
    public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int childDepth(Node<Integer> node){
        if(node == null){
            return 0;
        }

        int leftDepth = childDepth(node.left);
        int rightDepth = childDepth(node.right);

        if(rightDepth == -1 || leftDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) return -1;

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static boolean isBalanced(Node<Integer> tree) {
        // WRITE YOUR BRILLIANT CODE HERE

        return childDepth(tree) != -1;
    }

    // this function builds a tree from input; you don't have to modify it
    // learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
        String val = iter.next();
        if (val.equals("x")) return null;
        Node<T> left = buildTree(iter, f);
        Node<T> right = buildTree(iter, f);
        return new Node<T>(f.apply(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    /**
     * 1 2 4 x 7 x x 5 x x 3 x 6 x x -> true
     * 1 2 4 x 7 x x 5 x x 3 x 6 8 x x x -> false
     * 1 2 3 x x 4 x 6 x x 5 x x -> false
     * 1 2 3 x x 4 x 6 x x 5 x 7 x x -> true
     * 1 2 3 x x 4 x x 5 6 x 7 x x x -> false
     * 1 2 3 7 x x x 4 x x 5 6 x x x -> true
     * 1 2 3 4 x x 4 x x 3 x x 2 x x -> false
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Node<Integer> tree = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        boolean res = isBalanced(tree);
        System.out.println(res);
    }
}
