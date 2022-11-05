package dijkskra;

import java.util.Comparator;
import java.util.List;

public class Node implements Comparable<Node> {
    private int value;
    private List<Node> children;
    private Node from;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(getValue(), o.getValue());
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", children=" + children +
                ", from=" + from +
                '}';
    }
}
