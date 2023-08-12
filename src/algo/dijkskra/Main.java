package algo.dijkskra;

import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Node> pQ = new PriorityQueue<>(Node::compareTo);
        Node n1 = new Node(3);
        Node n2 = new Node(-1);
        Node n3 = new Node(6);
        Node n4 = new Node(2);

        pQ.addAll(List.of(n1,n2,n3,n4));

        while(!pQ.isEmpty()){
            System.out.println(pQ.poll());
        }
    }
}
