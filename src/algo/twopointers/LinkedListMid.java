package algo.twopointers;

public class LinkedListMid {
    public static void main(String[] args) {
        Nodee a = new Nodee(5);
        Nodee b = new Nodee(4);
        Nodee c = new Nodee(3, b);
        Nodee d = new Nodee(2, c);
        Nodee e = new Nodee(1, d);
        Nodee f = new Nodee(0, e);
//        Node g = new Node(6, f);
        System.out.println(findMid(f).val);
    }

    static Nodee findMid(Nodee node){
        Nodee b = node;
        while(node.next != null){
            node = node.next;
            b = b.next;
            if(node.next != null){
                node = node.next;
            }
        }

        return b;
    }
}

class Nodee {
    int val;
    Nodee next;

    Nodee(int val){
        this.val = val;
    }

    Nodee(int val, Nodee next){
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
